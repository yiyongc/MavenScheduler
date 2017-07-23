package assignment.bank.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IncorrectDateRangeException extends Exception {
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	public IncorrectDateRangeException() {
		super("Incorrect date format supplied");
	}
	
	public IncorrectDateRangeException(Date from, Date to) {
		super("Incorrect range supplied. From: " + dateFormat.format(from) + ", To: " + dateFormat.format(to));
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

