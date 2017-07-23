package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class MoneyDepositTests {
	
	ServiceBankImpl service = new ServiceBankImpl();
	
	@Test
	public void validDeposit() {
		Account validAccount = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 100);
		service.createAccount(validAccount);
		
		assertEquals("Success message", service.deposit(1, 10));
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountDeposit() {
		service.deposit(1, 10);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountDeposit() {
		Account account = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 100);
		service.createAccount(account);
		
		service.deposit(1, -3);
	}
	

}
