package assignment.bank.service;

import java.util.ArrayList;

import assignment.bank.beans.Account;
import assignment.bank.beans.Transaction;
import assignment.bank.repository.AccountRepoImpl;

public class ServiceBankImpl implements IServiceBank {
	
	private AccountRepoImpl accountRepo;

	public String createAccount(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account showBalance(int accNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account fundTransfer(int fromAcc, int toAcc, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account withdraw(int accNum, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account deposit(int accNum, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Transaction> printTransactions(int accNum, String dateFrom, String dateTo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Transaction> printTransactions(int accNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
