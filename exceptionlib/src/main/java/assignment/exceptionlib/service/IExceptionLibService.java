package assignment.exceptionlib.service;

import java.io.IOException;

public interface IExceptionLibService {
	
	public void init() throws IOException;
	
	public String handleException(String project, String module, Exception e) throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	
	public void printStorageContents();
	
	public int getStorageEntrySize();
	
	public int getStorageActionSize();
}
