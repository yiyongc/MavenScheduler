package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class AccountCreationTests {

	ServiceBankImpl service = new ServiceBankImpl();
	
	@Test
	public void createValidAccount() {
		Account validAccount = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 100);
		
		assertEquals("Success Message", service.createAccount(validAccount));
	}
	
	
	@Test (expected = assignment.bank.exceptions.InsufficientBalanceException.class)
	public void createInvalidAccount() {
		Account invalidAccount = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 99);
		
		service.createAccount(invalidAccount);
	}	

}
