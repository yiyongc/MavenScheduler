package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class MoneyWithdrawalTests {

	
	ServiceBankImpl service = new ServiceBankImpl();
	Account account = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 2000);
	{
		service.createAccount(account);
	}
	
	@Test
	public void validWithdrawal() {	
		assertEquals(1900, service.withdraw(1, 100).getAccBalance(), 0.01);
	}
	
	@Test (expected = assignment.bank.exceptions.InsufficientBalanceException.class) 
	public void insufficientBalanceForWithdrawal() {
		service.withdraw(1, 2001);
	}
	
	@Test (expected = assignment.bank.exceptions.WithdrawLimitException.class) 
	public void withdrawLimitExceeded() {
		service.withdraw(1, 500);
		service.withdraw(1, 500);
		service.withdraw(1, 1);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountWithdrawal() {
		service.withdraw(2, 10);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountWithdrawal() {	
		service.withdraw(1, -10);
	}
}
