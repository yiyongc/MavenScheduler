package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class ViewBalanceTests {

	ServiceBankImpl service = new ServiceBankImpl();
	Account account = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 1992);
	
	{
		service.createAccount(account);
	}
	
	@Test
	public void validViewBalance() {
		assertEquals(1992, service.showBalance(1).getAccBalance(), 0.01);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountView() {
		service.showBalance(2);
	}

}
