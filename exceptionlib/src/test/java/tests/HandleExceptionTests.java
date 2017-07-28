package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import assignment.exceptionlib.demoexceptions.InvalidAccountException;
import assignment.exceptionlib.service.ExceptionLibServiceImpl;
import assignment.exceptionlib.service.IExceptionLibService;

public class HandleExceptionTests {

	String file = "D:\\Users\\yichee\\Desktop\\Playground\\Assignment 3\\Testing.xml";
	
	IExceptionLibService service = new ExceptionLibServiceImpl(file);
	Logger logger = Logger.getLogger("Client Testing");
	
	@Before
	public void init() {
		try {
			service.init();
		} catch (IOException e) {
			logger.log(Level.FINE, e.getMessage(), e);
		}
	}
	
	@Test
	public void testExceptionHandled() {
		try {
			assertEquals("Actions executed: Email Log ", service.handleException("bank", "deposit", new InvalidAccountException()));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.log(Level.FINE, e.getMessage(), e);
		}
	}

}
