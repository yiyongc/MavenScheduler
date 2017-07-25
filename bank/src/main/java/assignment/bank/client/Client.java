package assignment.bank.client;

import java.text.ParseException;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.exceptions.WithdrawLimitException;
import assignment.bank.service.IServiceBank;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class Client {

	public static void main(String[] args) {
		IServiceBank service = new ServiceBankImpl();
		
		Account dummy = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 100);
		
		try {
			service.createAccount(dummy);
			service.deposit(1, 1);
			service.withdraw(1, 100);
		} catch (InvalidAccountException | InvalidAmountException | InvalidAccountCreationException | InsufficientBalanceException | WithdrawLimitException | ParseException e) {
			e.printStackTrace();
		}
		
	}

}
