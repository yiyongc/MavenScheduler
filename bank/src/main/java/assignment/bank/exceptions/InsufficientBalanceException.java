package assignment.bank.exceptions;

public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException(double amount, String function) {
		super("Unable to " + function + " $" + amount + " due to insufficient balance in the account.");
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
