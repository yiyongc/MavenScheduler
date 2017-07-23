package assignment.bank.repository;

import java.util.List;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;

public interface IAccountRepo {

	public String addAccount(Account account) throws InvalidAccountCreationException;
	
	public Account findOne(int accNumber) throws InvalidAccountException;
	
	@SuppressWarnings("rawtypes")
	public List findAll();
	
	public String deposit(int accNum, double amount) throws InvalidAccountException;
	
	public Account withdraw(int accNum, double amount) throws InsufficientBalanceException, InvalidAccountException;
	
	public Account fundTransfer(int fromAcc, int toAcc, double amount) throws InvalidAccountException, InsufficientBalanceException;
	
	public Account getBalance(int accNumber) throws InvalidAccountException;

	
}
