package assignment.bank.service;

import java.util.List;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.IncorrectDateRangeException;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;

public interface IServiceBank {

	public String createAccount(Account acc) throws InvalidAccountCreationException;

	public Account showBalance(int accNumber) throws InvalidAccountException;

	public Account fundTransfer(int fromAcc, int toAcc, double amount)
			throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException;

	public Account withdraw(int accNum, double amount) throws InsufficientBalanceException, InvalidAccountException,
			InvalidAmountException, WithdrawLimitException;

	public Account deposit(int accNum, double amount) throws InvalidAccountException, InvalidAmountException;

	@SuppressWarnings("rawtypes")
	public List printTransactions(int accNum, String dateFrom, String dateTo)
			throws IncorrectDateRangeException, InvalidAccountException;

	@SuppressWarnings("rawtypes")
	public List printTransactions(int accNum) throws InvalidAccountException;

}
