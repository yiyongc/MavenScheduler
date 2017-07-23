package assignment.bank.service;

import java.util.ArrayList;

import assignment.bank.beans.Account;
import assignment.bank.beans.Transaction;

public interface IServiceBank {

	public String createAccount(Account acc);
	
	public Account showBalance(int accNumber);
	
	public Account fundTransfer(int fromAcc, int toAcc, double amount);
	
	public Account withdraw(int accNum, double amount);
	
	public Account deposit(int accNum, double amount);
	
	public ArrayList<Transaction> printTransactions(int accNum, String dateFrom, String dateTo);
	
	public ArrayList<Transaction> printTransactions(int accNum);
	
}
