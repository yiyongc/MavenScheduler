package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.service.ServiceBankImpl;

public class MoneyDepositTests {

	ServiceBankImpl service = new ServiceBankImpl();

	@Test
	public void validDeposit() throws InvalidAccountCreationException, InvalidAccountException, InvalidAmountException {
		Account validAccount = new Account(1, 100);
		service.createAccount(validAccount);

		assertEquals(110, service.deposit(1, 10).getAccBalance(), 0.01);
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountDeposit() throws InvalidAccountException, InvalidAmountException {
		service.deposit(1, 10);
	}

	@Test(expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountDeposit()
			throws InvalidAccountCreationException, InvalidAccountException, InvalidAmountException {
		Account account = new Account(1, 100);
		service.createAccount(account);

		service.deposit(1, -3);
	}

}
