package assignment.bank;

import static org.junit.Assert.*;

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
	public void validWithdrawal() throws InsufficientBalanceException, InvalidAccountException, InvalidAmountException,
			WithdrawLimitException, InvalidAccountCreationException {
		setup();

		assertEquals(400, service.withdraw(1, 100).getAccBalance(), 0.01);
	}

	@Test(expected = assignment.bank.exceptions.InsufficientBalanceException.class)
	public void insufficientBalanceForWithdrawal() throws InsufficientBalanceException, InvalidAccountException,
			InvalidAmountException, WithdrawLimitException, InvalidAccountCreationException {
		setup();

		service.withdraw(1, 1000);
	}

	@Test(expected = assignment.bank.exceptions.WithdrawLimitException.class)
	public void withdrawLimitExceeded() throws InsufficientBalanceException, InvalidAccountException,
			InvalidAmountException, WithdrawLimitException, InvalidAccountCreationException {
		setup();

		service.withdraw(2, 500);
		service.withdraw(2, 500);
		service.withdraw(2, 1);
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountWithdrawal() throws InsufficientBalanceException, InvalidAccountException,
			InvalidAmountException, WithdrawLimitException {
		service.withdraw(3, 10);
	}

	@Test(expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountWithdrawal() throws InsufficientBalanceException, InvalidAccountException,
			InvalidAmountException, WithdrawLimitException {
		service.withdraw(1, -10);
	}
}
