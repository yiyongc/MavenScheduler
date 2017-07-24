package assignment.bank.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import assignment.bank.beans.Account;
import assignment.bank.beans.Transaction;
import assignment.bank.exceptions.IncorrectDateRangeException;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.repository.AccountRepoImpl;

public class ServiceBankImpl implements IServiceBank {

	private AccountRepoImpl accountRepo = new AccountRepoImpl();

	public String createAccount(Account acc) throws InvalidAccountCreationException {

		if (acc.getAccBalance() < 100)
			throw new InvalidAccountCreationException(acc.getAccBalance());

		return accountRepo.addAccount(acc);
	}

	public Account showBalance(int accNumber) throws InvalidAccountException {
		if (accNumber <= 0)
			throw new InvalidAccountException();

		return accountRepo.getBalance(accNumber);
	}

	public Account fundTransfer(int fromAcc, int toAcc, double amount)
			throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException {
		// input checking
		if (fromAcc <= 0 || toAcc <= 0)
			throw new InvalidAccountException();
		if (amount <= 0)
			throw new InvalidAmountException(amount, "transfer");

		return accountRepo.fundTransfer(fromAcc, toAcc, amount);
	}

	public Account withdraw(int accNum, double amount) throws InsufficientBalanceException, InvalidAccountException,
			InvalidAmountException, WithdrawLimitException {
		if (accNum <= 0)
			throw new InvalidAccountException();
		if (amount <= 0)
			throw new InvalidAmountException(amount, "withdraw");

		return accountRepo.withdraw(accNum, amount);

	}

	public Account deposit(int accNum, double amount) throws InvalidAccountException, InvalidAmountException {
		if (accNum <= 0)
			throw new InvalidAccountException();
		if (amount <= 0)
			throw new InvalidAmountException(amount, "withdraw");

		return accountRepo.deposit(accNum, amount);
	}

	@SuppressWarnings("rawtypes")
	public List printTransactions(int accNum, String dateFrom, String dateTo)
			throws IncorrectDateRangeException, InvalidAccountException {
		if (accNum <= 0)
			throw new InvalidAccountException();

		Account account = accountRepo.findOne(accNum);

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date fromDate = sdf.parse(dateFrom + " 00:00:00");
			Date toDate = sdf.parse(dateTo + " 23:59:59");
			
			if (fromDate.after(toDate))
				throw new IncorrectDateRangeException(fromDate, toDate);

			List<Transaction> searchResult = new ArrayList<>();

			@SuppressWarnings("unchecked")
			ArrayList<Transaction> transHistory = (ArrayList<Transaction>) account.getTransactions();

			for (Transaction transaction : transHistory) {
				Date transDate = transaction.getDate();
				if (dateWithinRange(transDate, fromDate, toDate))
					searchResult.add(transaction);
			}

			return searchResult;

		} catch (ParseException e) {
			throw new IncorrectDateRangeException();
		}
	}

	private boolean dateWithinRange(Date date, Date fromDate, Date toDate) {
		return date.equals(fromDate) || date.equals(toDate) || (date.after(fromDate) && date.before(toDate));
	}

	@SuppressWarnings("rawtypes")
	public List printTransactions(int accNum) throws InvalidAccountException {
		if (accNum <= 0)
			throw new InvalidAccountException();

		Account account = accountRepo.findOne(accNum);

		@SuppressWarnings("unchecked")
		List<Transaction> transHistory = (ArrayList<Transaction>) account.getTransactions();
		List<Transaction> searchResult = new ArrayList<>();

		int lengthOfHistory = transHistory.size();
		int startOfSearch;

		if (lengthOfHistory > 10)
			startOfSearch = lengthOfHistory - 10;
		else
			startOfSearch = 0;

		for (int i = 0; i < 10; i++) {
			if (startOfSearch >= lengthOfHistory)
				break;
			searchResult.add(transHistory.get(startOfSearch++));
		}

		return searchResult;
	}

}
