package assignment.exceptionlib.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import assignment.exceptionlib.beans.Action;
import assignment.exceptionlib.beans.ProjectInfo;
import assignment.exceptionlib.storage.ExceptionHandlerStorage;
import assignment.exceptionlib.storage.IExceptionHandlerStorage;
import assignment.exceptionlib.utility.XMLReader;

public class Client {
	
	private Client() {}

	public static void main(String[] args) {
		Map<ProjectInfo, Set<Action>> storageMap = new HashMap<>();
		IExceptionHandlerStorage storage = new ExceptionHandlerStorage(storageMap);
		
		String file = "D:\\Users\\yichee\\Desktop\\Playground\\Assignment 3\\Testing.xml";
		
		XMLReader reader = new XMLReader(file, storage);
		reader.parseDocument();
		
		Set<Entry<ProjectInfo, Set<Action>>> storageSet = storageMap.entrySet();
		
		Iterator<Entry<ProjectInfo, Set<Action>>> it = storageSet.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	

}
