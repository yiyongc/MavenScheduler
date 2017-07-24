package assignment.bank.repository;

import java.text.ParseException;
import java.util.List;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.WithdrawLimitException;

public interface IAccountRepo {

	public String addAccount(Account account) throws InvalidAccountCreationException;

	public Account findOne(int accNumber) throws InvalidAccountException;

	@SuppressWarnings("rawtypes")
	public List findAll();

	public Account deposit(int accNum, double amount) throws InvalidAccountException;

	public Account withdraw(int accNum, double amount)
			throws InsufficientBalanceException, InvalidAccountException, WithdrawLimitException, ParseException;

	public Account fundTransfer(int fromAcc, int toAcc, double amount)
			throws InvalidAccountException, InsufficientBalanceException;

	public Account getBalance(int accNumber) throws InvalidAccountException;

}
