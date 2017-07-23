package assignment.bank.repository;

import java.util.ArrayList;

import assignment.bank.beans.Account;

public interface IAccountRepo {

	public String addAccount();
	
	public Account findOne(int accNumber);
	
	public ArrayList<Account> findAll();
	
	public String deposit(int accNum, double Amount);
	
	public Account withdraw(int accNum, double amount);
	
	public Account fundTransfer(int fromAcc, int toAcc, double amount);
	
	public Account getBalance(int accNumber);

	
}
