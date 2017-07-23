package assignment.bank.exceptions;

public class WithdrawLimitException extends Exception {
	
	public WithdrawLimitException(double amount) {
		super("Unable to withdraw $" + amount + " as account has hit its withdrawal limit for the day.");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
