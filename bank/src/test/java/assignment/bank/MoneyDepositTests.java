package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.beans.Customer;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.repository.AccountRepoImpl;
import assignment.bank.service.ServiceBankImpl;

public class MoneyDepositTests {

	ServiceBankImpl service = new ServiceBankImpl(new AccountRepoImpl());
	Account account = null;
	
	public void setup() {
		try {
			account = service.createAccount(new Customer("Tom"), 100);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validDeposit() {
		setup();

		try {
			assertEquals(110, service.deposit(account.getAccNumber(), 10).getAccBalance(), 0.01);
		} catch (InvalidAccountException | InvalidAmountException e) {
			e.printStackTrace();
		} 
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountDeposit() throws InvalidAccountException {
		try {
			service.deposit(1, 10);
		} catch (InvalidAmountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountDeposit() throws InvalidAmountException {
		setup();

		try {
			service.deposit(account.getAccNumber(), -3);
		} catch (InvalidAccountException e) {
			e.printStackTrace();
			//Need to log here 
		}
	}

}
