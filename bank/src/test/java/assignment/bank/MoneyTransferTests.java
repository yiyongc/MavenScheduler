package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.service.ServiceBankImpl;

public class MoneyTransferTests {

	ServiceBankImpl service = new ServiceBankImpl();

	public void setup() throws InvalidAccountCreationException {
		Account acc1 = new Account(1, 100);
		Account acc2 = new Account(2, 100);
		service.createAccount(acc1);
		service.createAccount(acc2);
	}

	@Test
	public void validTransfer() {
		try {
			setup();
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}

		try {
			assertEquals(90, service.fundTransfer(1, 2, 10).getAccBalance(), 0.01);
		} catch (InvalidAccountException | InsufficientBalanceException | InvalidAmountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InsufficientBalanceException.class)
	public void insufficientBalanceTransfer() throws InsufficientBalanceException {
		try {
			setup();
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}

		try {
			service.fundTransfer(1, 2, 200);
		} catch (InvalidAccountException | InvalidAmountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransfer() throws InvalidAccountException {
		try {
			service.fundTransfer(1, 3, 10);
		} catch (InsufficientBalanceException | InvalidAmountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransfer2() throws InvalidAccountException {
		try {
			service.fundTransfer(3, 2, 10);
		} catch (InsufficientBalanceException | InvalidAmountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountTransfer()	throws InvalidAmountException {
		try {
			service.fundTransfer(1, 2, -10);
		} catch (InvalidAccountException | InsufficientBalanceException e) {
			e.printStackTrace();
		}
	}
}
