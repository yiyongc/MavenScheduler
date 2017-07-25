package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.beans.Customer;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.repository.AccountRepoImpl;
import assignment.bank.service.IServiceBank;
import assignment.bank.service.ServiceBankImpl;

public class ViewBalanceTests {

	IServiceBank service = new ServiceBankImpl(new AccountRepoImpl());
	Account account = null;
	
	public void setup() {
		try {
			account = service.createAccount(new Customer("Tom"), 1992);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validViewBalance() {
		setup();
		
		try {
			assertEquals(1992, service.showBalance(account.getAccNumber()).getAccBalance(), 0.01);
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountView() throws InvalidAccountException {
		service.showBalance(1);
	}

}
