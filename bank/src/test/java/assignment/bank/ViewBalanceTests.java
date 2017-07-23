package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class ViewBalanceTests {

	static ServiceBankImpl service = new ServiceBankImpl();
	
	static
	{
		Account account = new Account(1, 1992);
		try {
			service.createAccount(account);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validViewBalance() throws InvalidAccountException {
		assertEquals(1992, service.showBalance(1).getAccBalance(), 0.01);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountView() throws InvalidAccountException {
		service.showBalance(2);
	}

}
