package assignment.bank.utility;

public class UniqueNumberGenerator {
	
	private static int accountNo = 0;
	private static int transactionNo = 0;
	
	private UniqueNumberGenerator(){}
	
	public static int generateUniqueAccNo() {
		return accountNo++;
	}
	
	public static int generateUniqueTransNo() {
		return transactionNo++;
	}
}
