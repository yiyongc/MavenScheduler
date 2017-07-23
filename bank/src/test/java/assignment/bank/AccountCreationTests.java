package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.service.ServiceBankImpl;

public class AccountCreationTests {

	ServiceBankImpl service = new ServiceBankImpl();

	@Test
	public void createValidAccount() throws InvalidAccountCreationException {
		Account validAccount = new Account(1, 100);

		assertEquals("Account successfully created.", service.createAccount(validAccount));
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountCreationException.class)
	public void createInvalidAccount() throws InvalidAccountCreationException {
		Account invalidAccount = new Account(1, 99);

		service.createAccount(invalidAccount);
	}

}
