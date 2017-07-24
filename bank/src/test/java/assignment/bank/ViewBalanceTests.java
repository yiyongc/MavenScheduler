package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.service.ServiceBankImpl;

public class ViewBalanceTests {

	ServiceBankImpl service = new ServiceBankImpl();

	public void setup() throws InvalidAccountCreationException {
		Account account = new Account(1, 1992);
		service.createAccount(account);
	}

	@Test
	public void validViewBalance() {
		try {
			setup();
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(1992, service.showBalance(1).getAccBalance(), 0.01);
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountView() throws InvalidAccountException {
		service.showBalance(2);
	}

}
