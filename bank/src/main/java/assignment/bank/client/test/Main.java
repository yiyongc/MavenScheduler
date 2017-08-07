package assignment.bank.client.test;

import assignment.bank.beans.Customer;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.repository.AccountRepoImpl;
import assignment.bank.repository.IAccountRepo;
import assignment.bank.service.IServiceBank;
import assignment.bank.service.ServiceBankImpl;

public class Main {

	public static void main(String[] args) {
		IAccountRepo accRepo = new AccountRepoImpl();
		IServiceBank service = new ServiceBankImpl(accRepo);
		System.out.println(Thread.currentThread().isDaemon());
		BankWithdrawRunner runner = new BankWithdrawRunner(service);
		
		try {
			service.createAccount(new Customer("Sagar"), 500);
		} catch (InvalidAccountCreationException e) {
			e.printStackTrace();
		}
		
		Thread t1 = new Thread(runner);
		Thread t2 = new Thread(runner);
		
		t1.start();
		t2.start();
		
	}
	
}
