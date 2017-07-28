package assignment.exceptionlib.storage;

import java.util.Set;

import assignment.exceptionlib.beans.Action;
import assignment.exceptionlib.beans.ProjectInfo;

public interface IExceptionHandlerStorage {
	
	public boolean addProjectInfo(ProjectInfo pInfo); 
		
	public Set<Action> getActions(ProjectInfo pInfo); 
	
}
