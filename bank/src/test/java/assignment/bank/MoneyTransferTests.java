package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.service.ServiceBankImpl;
import assignment.bank.utility.UniqueNumberGenerator;

public class MoneyTransferTests {

	ServiceBankImpl service = new ServiceBankImpl();
	Account acc1 = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 100);
	Account acc2 = new Account(UniqueNumberGenerator.generateUniqueAccNo(), 100);
	
	{
		service.createAccount(acc1);
		service.createAccount(acc2);
	}
	
	@Test
	public void validTransfer() {
		assertEquals(90, service.fundTransfer(1, 2, 10).getAccBalance(), 0.01);
	}
	
	@Test (expected = assignment.bank.exceptions.InsufficientBalanceException.class)
	public void insufficientBalanceTransfer() {
		service.fundTransfer(1, 2, 200);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransfer() {
		service.fundTransfer(1, 3, 10);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransfer2() {
		service.fundTransfer(3, 2, 10);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountTransfer() {
		service.fundTransfer(1, 2, -10);
	}
}
