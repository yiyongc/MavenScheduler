package assignment.exceptionlib.storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import assignment.exceptionlib.beans.Action;
import assignment.exceptionlib.beans.ProjectInfo;

public class ExceptionHandlerStorage implements IExceptionHandlerStorage {
	private Map<ProjectInfo, Set<Action>> handlerStorage;
	
	
	public ExceptionHandlerStorage(Map<ProjectInfo, Set<Action>> handlerStorage) {
		this.handlerStorage = handlerStorage;
	}

	public boolean addProjectInfo(ProjectInfo pInfo) {
		if (handlerStorage.containsKey(pInfo))
			return false;
		else {
			Set<Action> actions = new HashSet<>();
			handlerStorage.put(pInfo, actions);
			return true;
		}
	}
		
	public Set<Action> getActions(ProjectInfo pInfo) {
		if (!handlerStorage.containsKey(pInfo))
			return Collections.emptySet();
		else {
			return handlerStorage.get(pInfo);
		}
	
	}
	
}
