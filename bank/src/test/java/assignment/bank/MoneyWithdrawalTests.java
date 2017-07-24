package assignment.bank;

import static org.junit.Assert.*;

import java.text.ParseException;
import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.service.ServiceBankImpl;

public class MoneyWithdrawalTests {

	ServiceBankImpl service = new ServiceBankImpl();

	public void setup() throws InvalidAccountCreationException {

		Account normalAccount = new Account(1, 500);
		service.createAccount(normalAccount);
		Account richAccount = new Account(2, 2000);
		service.createAccount(richAccount);

	}

	@Test
	public void validWithdrawal() {
		try {
			setup();
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}

		try {
			assertEquals(400, service.withdraw(1, 100).getAccBalance(), 0.01);
		} catch (InsufficientBalanceException | InvalidAccountException | InvalidAmountException
				| WithdrawLimitException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Test (expected = assignment.bank.exceptions.InsufficientBalanceException.class)
	public void insufficientBalanceForWithdrawal() throws InsufficientBalanceException {
		try {
			setup();
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}

		try {
			service.withdraw(1, 1000);
		} catch (InvalidAccountException | InvalidAmountException | WithdrawLimitException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.WithdrawLimitException.class)
	public void withdrawLimitExceeded() throws WithdrawLimitException {
		try {
			setup();
		} catch (InvalidAccountCreationException e) {

			e.printStackTrace();
		}

		try {
			service.withdraw(2, 500);
			service.withdraw(2, 500);
			service.withdraw(2, 1);
		} catch (InsufficientBalanceException | InvalidAccountException | InvalidAmountException | ParseException e) {
	
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountWithdrawal() throws InvalidAccountException {
		try {
			service.withdraw(3, 10);
		} catch (InsufficientBalanceException | InvalidAmountException | WithdrawLimitException | ParseException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountWithdrawal() throws InvalidAmountException {
		try {
			service.withdraw(1, -10);
		} catch (InsufficientBalanceException | InvalidAccountException | WithdrawLimitException | ParseException e) {
			e.printStackTrace();
		}
	}
}
