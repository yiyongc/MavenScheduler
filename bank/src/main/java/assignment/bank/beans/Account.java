package assignment.bank.beans;

import java.util.ArrayList;

public class Account {
	
	private int accNumber;
	private double accBalance;
	private ArrayList<Transaction> transactionHistory;
	
	public Account(int accNumber, double balance) {
		this.accNumber = accNumber;
		this.accBalance = balance;
		this.transactionHistory = new ArrayList<Transaction>();
	}
	
	public int getAccNumber() {
		return accNumber;
	}
	
	public double getAccBalance() {
		return accBalance;
	}
	
	public void setAccBalance(double newBalance) {
		this.accBalance = newBalance;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactionHistory;
	}
	
	public void addTransaction(Transaction transaction) {
		transactionHistory.add(transaction);
	}
}
