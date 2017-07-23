package assignment.bank;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.service.ServiceBankImpl;

public class ViewBalanceTests {

	static ServiceBankImpl service = new ServiceBankImpl();
	private static final Logger LOGGER = Logger.getLogger( ViewBalanceTests.class.getName() );
	
	static
	{
		Account account = new Account(1, 1992);
		try {
			service.createAccount(account);
		} catch (InvalidAccountCreationException e) {
			LOGGER.log(Level.FINE, e.getMessage());
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
