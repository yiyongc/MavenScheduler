package assignment.bank.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import assignment.bank.beans.Account;
import assignment.bank.beans.Transaction;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.utility.UniqueNumberGenerator;

public class AccountRepoImpl implements IAccountRepo {
	
	private ArrayList<Account> accounts = new ArrayList<Account>();

	public String addAccount(Account account) throws InvalidAccountCreationException {
		
		if (accounts.contains(account))
			throw new InvalidAccountCreationException(account.getAccNumber());
		
		accounts.add(account);
		
		return "Account successfully created.";
		
	}

	public Account findOne(int accNumber) throws InvalidAccountException {
		
		for (Account account : accounts) {
			if (account.getAccNumber() == accNumber)
				return account;
		}
		
		throw new InvalidAccountException();
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return accounts;
	}

	public Account deposit(int accNum, double amount) throws InvalidAccountException {
		
		for (Account account : accounts) {
			if (account.getAccNumber() == accNum) {
				double newBalance = account.getAccBalance() + amount;
				account.setAccBalance(newBalance);
				
				Transaction transaction = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(),
														  new Date(),
														  amount,
														  "Deposit of $"+amount,
														  newBalance);
				
				account.addTransaction(transaction);
				
				return account;
			}
		}
		
		throw new InvalidAccountException();
	}

	public Account withdraw(int accNum, double amount) throws InvalidAccountException, WithdrawLimitException, InsufficientBalanceException {
		Account accountFound = null;
		
		for (Account account : accounts) {
			if (account.getAccNumber() == accNum) 
				accountFound = account;
		}
		
		if (accountFound == null)
			throw new InvalidAccountException();
		
		if (withdrawLimitReached(accountFound, amount))
			throw new WithdrawLimitException(amount);
		
		double newBalance = accountFound.getAccBalance() - amount;
		
		if (newBalance < 0)
			throw new InsufficientBalanceException(amount ,"withdraw");
		else {
			accountFound.setAccBalance(newBalance);
			
			Transaction transaction = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(),
					  new Date(),
					  -amount,
					  "Withdrawal of $"+amount,
					  newBalance);
			
			accountFound.addTransaction(transaction);
			
			return accountFound;
		}
	}
		
	private boolean withdrawLimitReached(Account acc, double amount) {
		Date currDate = new Date();
		double total = 0;
		
		@SuppressWarnings("unchecked")
		ArrayList<Transaction> transactionHistory = (ArrayList<Transaction>) acc.getTransactions();
		
		for (Transaction transaction : transactionHistory) {
			if (transaction.getDate().equals(currDate)){
				double amt = transaction.getAmount();
				if (amt < 0) //negative is withdrawal
					total += amt;
			}
		}
	
		return Math.abs(total) + amount > 1000;
	}

	public Account fundTransfer(int fromAcc, int toAcc, double amount) throws InvalidAccountException, InsufficientBalanceException {
		Account accFrom = null;
		Account accTo = null;
		
		//Search for accounts related
		for (Account account : accounts) {
			if (account.getAccNumber() == fromAcc)
				accFrom = account;
			else if (account.getAccNumber() == toAcc)
				accTo = account;
		}
		
		if (accFrom == null || accTo == null)
			throw new InvalidAccountException();
		else {
			if (accFrom.getAccBalance() < amount)
				throw new InsufficientBalanceException(amount, "transfer");
			else {
				double newBalanceSource = accFrom.getAccBalance() - amount;
				double newBalanceDestination = accTo.getAccBalance() + amount;
				accFrom.setAccBalance(newBalanceSource);
				accTo.setAccBalance(newBalanceDestination);	
				
				Date currDate = new Date();
				
				Transaction transactionSource = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(),
						currDate,
						-amount,
						"Transfer of $"+amount + " to " + toAcc,
						newBalanceSource);
				
				accFrom.addTransaction(transactionSource);
				
				Transaction transactionDest = new Transaction(UniqueNumberGenerator.generateUniqueTransNo(),
						currDate,
						amount,
						"Received $"+amount + " from " + fromAcc,
						newBalanceDestination);
				
				accTo.addTransaction(transactionDest);
				
				return accFrom;
			}
		}
	}

	public Account getBalance(int accNumber) throws InvalidAccountException {
		
		for (Account account : accounts) {
			if (account.getAccNumber() == accNumber) {
				return account;
			}
		}
		
		throw new InvalidAccountException();
	}


}
