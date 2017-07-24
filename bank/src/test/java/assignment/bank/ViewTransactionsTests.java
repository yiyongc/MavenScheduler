package assignment.bank;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.beans.Transaction;
import assignment.bank.exceptions.IncorrectDateRangeException;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class ViewTransactionsTests {

	ServiceBankImpl service = new ServiceBankImpl();

	@Test
	public void viewLastTenTransactions() throws InvalidAccountException, InvalidAmountException,
			InsufficientBalanceException, WithdrawLimitException, InvalidAccountCreationException, ParseException {
		Account account = new Account(1, 1992);
		service.createAccount(account);

		// 5 deposits, 6 withdrawals
		for (int i = 0; i < 5; i++)
			service.deposit(1, 10);
		for (int j = 0; j < 6; j++)
			service.withdraw(1, 5);

		@SuppressWarnings("unchecked")
		List<Transaction> dummy = (ArrayList<Transaction>) account.getTransactions();
		dummy.remove(10);

		assertEquals(dummy, service.printTransactions(1));
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransactions() throws InvalidAccountException {
		service.printTransactions(2);
	}

	@Test
	public void viewDateRangeTransactions() throws InvalidAccountCreationException, InvalidAccountException,
			InvalidAmountException, IncorrectDateRangeException {
		Calendar c = Calendar.getInstance();
		c.set(2017, 0, 10);
		Date myDate = c.getTime();
		Transaction dummyTransaction = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(), myDate, 0,
				"Dummy transaction", 100);
		Account account = new Account(1, 1992);
		service.createAccount(account);
		account.addTransaction(dummyTransaction);

		for (int i = 0; i < 10; i++)
			service.deposit(1, 10);

		List<Transaction> resultantSearch = new ArrayList<>();
		resultantSearch.add(dummyTransaction);

		assertEquals(resultantSearch, service.printTransactions(1, "2017/1/10", "2017/2/10"));
	}

	@Test(expected = assignment.bank.exceptions.IncorrectDateRangeException.class)
	public void invalidDateRangeTransactions()
			throws IncorrectDateRangeException, InvalidAccountException, InvalidAccountCreationException {
		Account account = new Account(1, 1992);
		service.createAccount(account);

		service.printTransactions(1, "2017/7/10", "2017/6/10");
	}

	@Test
	public void noTransactionHistory()
			throws InvalidAccountException, IncorrectDateRangeException, InvalidAccountCreationException {
		List<Transaction> emptyHistory = new ArrayList<>();
		Account account = new Account(1, 1992);
		service.createAccount(account);

		assertEquals(emptyHistory, service.printTransactions(1));
		assertEquals(emptyHistory, service.printTransactions(1, "2017/5/5", "2017/5/5"));
	}

}
