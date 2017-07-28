package assignment.exceptionlib.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import assignment.exceptionlib.actions.Action;
import assignment.exceptionlib.beans.ActionInfo;
import assignment.exceptionlib.beans.ProjectInfo;
import assignment.exceptionlib.storage.ExceptionHandlerStorage;
import assignment.exceptionlib.storage.IExceptionHandlerStorage;
import assignment.exceptionlib.utility.XMLReader;

public class ExceptionLibServiceImpl implements IExceptionLibService {
	
	XMLReader reader;
	IExceptionHandlerStorage storage;
	String file;
	
	public ExceptionLibServiceImpl(String file) {
		this.file = file;
	}
	
	public void init() throws IOException {
		Map<ProjectInfo, Set<ActionInfo>> storageMap = new HashMap<>();
		storage = new ExceptionHandlerStorage(storageMap);
		
		reader = new XMLReader(file, storage);
		
		reader.parseDocument();
	}
		
	public String handleException(String project, String module, Exception e) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		ProjectInfo pInfo = new ProjectInfo(project, module, e.getClass().getSimpleName());
		
		Set<ActionInfo> actionSet = storage.getActions(pInfo);
		
		if (actionSet.equals(Collections.emptySet()))
			return "";
			
		Iterator<ActionInfo> it = actionSet.iterator();
		
		StringBuilder sb = new StringBuilder("Actions executed: ");
		
		while (it.hasNext()) {
			ActionInfo actionInfo = it.next();
			String className = actionInfo.getName();
			char firstChar = Character.toUpperCase(className.charAt(0));
			String name = firstChar + className.substring(1);
			Class<?> c = Class.forName("assignment.exceptionlib.actions." + name);
			Object o = c.newInstance();
			if (o instanceof Action) {
				Action action = (Action) o;
				action.execute(actionInfo.getAttributeMap());
				sb.append(name + " ");
			}
		}
		
		return sb.toString();
	}

	//Methods below are for debugging purposes
	public int getStorageEntrySize() {
		Set<Entry<ProjectInfo, Set<ActionInfo>>> set = storage.getHandlerStorage().entrySet();
		
		return set.size();
	}
	
	
	public int getStorageActionSize() {
		return storage.getHandlerStorage().values().size();
	}
	
	public void printStorageContents() {
		Map<ProjectInfo, Set<ActionInfo>> storageMap = storage.getHandlerStorage();
	
		Set<Entry<ProjectInfo, Set<ActionInfo>>> storageSet = storageMap.entrySet();
		
		Iterator<Entry<ProjectInfo, Set<ActionInfo>>> it = storageSet.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
