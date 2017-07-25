package assignment.bank;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.beans.Customer;
import assignment.bank.beans.Transaction;
import assignment.bank.exceptions.IncorrectDateRangeException;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.repository.AccountRepoImpl;
import assignment.bank.service.IServiceBank;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class ViewTransactionsTests {

	IServiceBank service = new ServiceBankImpl(new AccountRepoImpl());
	Account account = null;

	public void setup() {
		try {
			account = service.createAccount(new Customer("Tom"), 1992);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void viewLastTenTransactions() {
		setup();

		// 5 deposits, 6 withdrawals
		for (int i = 0; i < 5; i++)
			try {
				service.deposit(account.getAccNumber(), 10);
			} catch (InvalidAccountException | InvalidAmountException e) {
				e.printStackTrace();
			}
		for (int j = 0; j < 6; j++)
			try {
				service.withdraw(account.getAccNumber(), 5);
			} catch (InsufficientBalanceException | InvalidAccountException | InvalidAmountException
					| WithdrawLimitException | ParseException e) {
				e.printStackTrace();
			}

		List<Transaction> dummy = (ArrayList<Transaction>) account.getTransactions();
		dummy.remove(10);

		try {
			assertEquals(dummy, service.printTransactions(account.getAccNumber()));
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransactions() throws InvalidAccountException {
		service.printTransactions(2);
	}

	@Test
	public void viewDateRangeTransactions() {
		Calendar c = Calendar.getInstance();
		c.set(2017, 0, 10);
		Date myDate = c.getTime();
		Transaction dummyTransaction = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(), myDate, 0,
				"Dummy transaction", 100);
		setup();
		account.addTransaction(dummyTransaction);

		for (int i = 0; i < 10; i++)
			try {
				service.deposit(account.getAccNumber(), 10);
			} catch (InvalidAccountException | InvalidAmountException e) {
				e.printStackTrace();
			}

		List<Transaction> resultantSearch = new ArrayList<>();
		resultantSearch.add(dummyTransaction);

		try {
			assertEquals(resultantSearch, service.printTransactions(account.getAccNumber(), "2017/1/10", "2017/2/10"));
		} catch (IncorrectDateRangeException | InvalidAccountException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = assignment.bank.exceptions.IncorrectDateRangeException.class)
	public void invalidDateRangeTransactions() throws IncorrectDateRangeException {
		setup();

		try {
			service.printTransactions(account.getAccNumber(), "2017/7/10", "2017/6/10");
		} catch (InvalidAccountException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void noTransactionHistory() {
		List<Transaction> emptyHistory = new ArrayList<>();
		setup();

		try {
			assertEquals(emptyHistory, service.printTransactions(account.getAccNumber()));
			assertEquals(emptyHistory, service.printTransactions(account.getAccNumber(), "2017/5/5", "2017/5/5"));
		} catch (InvalidAccountException | IncorrectDateRangeException e) {
			e.printStackTrace();
		}
		
	}

}
