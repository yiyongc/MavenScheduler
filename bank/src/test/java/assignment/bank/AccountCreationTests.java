package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Customer;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.repository.AccountRepoImpl;
import assignment.bank.service.ServiceBankImpl;

public class AccountCreationTests {

	ServiceBankImpl service = new ServiceBankImpl(new AccountRepoImpl());

	@Test
	public void createValidAccount()  {
		
		try {
			assertEquals(100, service.createAccount(new Customer("Tom"), 100).getAccBalance(), 0.01);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountCreationException.class)
	public void createInvalidAccount() throws InvalidAccountCreationException {
		service.createAccount(new Customer("Tom"), 99);
	}
	
	@Test(expected = assignment.bank.exceptions.InvalidAccountCreationException.class)
	public void createAccountWithoutName() throws InvalidAccountCreationException {
		service.createAccount(new Customer(""), 99);
	}

}
