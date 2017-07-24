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
	public void validDeposit() {
		Account validAccount = new Account(1, 100);
		try {
			service.createAccount(validAccount);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}

		try {
			assertEquals(110, service.deposit(1, 10).getAccBalance(), 0.01);
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
		Account account = new Account(1, 100);
		try {
			service.createAccount(account);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}

		try {
			service.deposit(1, -3);
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
	}

}
