package assignment.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment.bank.beans.Account;
import assignment.bank.exceptions.InsufficientBalanceException;
import assignment.bank.exceptions.InvalidAccountCreationException;
import assignment.bank.exceptions.InvalidAccountException;
import assignment.bank.exceptions.InvalidAmountException;
import assignment.bank.service.ServiceBankImpl;

public class MoneyTransferTests {

	static ServiceBankImpl service = new ServiceBankImpl();
	
	
	static
	{
		Account acc1 = new Account(1, 100);
		Account acc2 = new Account(2, 100);
		try {
			service.createAccount(acc1);
			service.createAccount(acc2);
		} catch (InvalidAccountCreationException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void validTransfer() throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException {
		assertEquals(90, service.fundTransfer(1, 2, 10).getAccBalance(), 0.01);
	}
	
	@Test (expected = assignment.bank.exceptions.InsufficientBalanceException.class)
	public void insufficientBalanceTransfer() throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException {
		service.fundTransfer(1, 2, 200);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransfer() throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException {
		service.fundTransfer(1, 3, 10);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAccountException.class)
	public void invalidAccountTransfer2() throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException {
		service.fundTransfer(3, 2, 10);
	}
	
	@Test (expected = assignment.bank.exceptions.InvalidAmountException.class)
	public void invalidAmountTransfer() throws InvalidAccountException, InsufficientBalanceException, InvalidAmountException {
		service.fundTransfer(1, 2, -10);
	}
}
