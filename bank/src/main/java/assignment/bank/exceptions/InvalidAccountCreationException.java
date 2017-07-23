package assignment.bank.exceptions;

public class InvalidAccountCreationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountCreationException(double amount) {
		super("Unable to create account with $" + amount + " as the minimum required amount is $100.");
	}

	public InvalidAccountCreationException(int accNum) {
		super("Unable to create account as  account number: " + accNum + " already exists.");
	}

}
