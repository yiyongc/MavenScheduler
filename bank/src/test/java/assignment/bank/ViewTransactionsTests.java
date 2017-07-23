package assignment.bank;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.beans.Transaction;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class ViewTransactionsTests {

	ServiceBankImpl service = new ServiceBankImpl();
	Account account = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 1992);
	
	{
		service.createAccount(account);
	}
	
	@Test
	public void viewLastTenTransactions() {
		for (int i = 0; i < 5; i++)
			service.deposit(1, 10);
		for (int j = 0; j < 6; j++)
			service.withdraw(1, 5);
		
		//5 deposits, 6 withdrawals
		ArrayList<Transaction> dummy = account.getTransactions();
		dummy.remove(10);
		
		assertEquals(dummy, service.printTransactions(1));
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransactions() {
		service.printTransactions(2);
	}
	
	@Test
	public void viewDateRangeTransactions() {
		Calendar c = Calendar.getInstance();
		c.set(2017, 0, 10);
		Date myDate = c.getTime();
		Transaction dummyTransaction = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(),
														myDate,
														0, 
														"Dummy transaction", 
														100);
		account.addTransaction(dummyTransaction);
		
		for (int i = 0; i < 10; i++)
			service.deposit(1, 10);
		
		ArrayList<Transaction> resultantSearch = new ArrayList<Transaction>();
		resultantSearch.add(dummyTransaction);
		
		assertEquals(resultantSearch, service.printTransactions(1, "2017/1/10", "2017/2/10"));
	}
	
	@Test (expected = assignment.bank.exceptions.IncorrectDateRangeException.class)
	public void invalidDateRangeTransactions() {
		service.printTransactions(1, "2017/7/10", "2017/6/10");
	}
	
	@Test
	public void noTransactionHistory() {
		ArrayList<Transaction> emptyHistory = new ArrayList<Transaction>();
		
		assertEquals(emptyHistory, service.printTransactions(1));
		assertEquals(emptyHistory, service.printTransactions(1, "2017/5/5", "2017/5/5"));
	}
	
	
	
}
