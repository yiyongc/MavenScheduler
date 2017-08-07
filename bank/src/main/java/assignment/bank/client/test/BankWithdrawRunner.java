package assignment.bank.client.test;

import java.text.ParseException;

import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.service.IServiceBank;

public class BankWithdrawRunner implements Runnable {

	IServiceBank service;
	
	public BankWithdrawRunner(IServiceBank service) {
		this.service = service;
	}
	
	
	public void run() {
		try {
			service.withdraw(1, 300);
			System.out.println(service.showBalance(1).getAccBalance());
		} catch (InsufficientBalanceException | InvalidAccountException | InvalidAmountException
				| WithdrawLimitException | ParseException e) {
			e.printStackTrace();
		}
	}
}
