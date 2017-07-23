package assignment.bank.beans;

import java.util.Date;

public class Transaction {

	private int transID;
	private Date date;
	private double amount;
	private String description;
	private double balance;
	
	public Transaction(int transID, Date date, double amount, String desc, double balance) {
		this.transID = transID;
		this.date = date;
		this.amount = amount;
		this.description = desc;
		this.balance = balance;
	}
	
	public int getTransID() {
		return transID;
	}
	
	public Date getDate() {
		return date;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getBalance() {
		return balance;
	}

	
}
