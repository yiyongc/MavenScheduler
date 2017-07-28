package assignment.exceptionlib.beans;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Action {
	
	private String name;
	private Map<String, String> attributeMap;
	
	public Action(String name, Map<String, String> attributeMap) {
		this.name = name;
		this.attributeMap = attributeMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<String, String> attributeMap) {
		this.attributeMap = attributeMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeMap == null) ? 0 : attributeMap.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Action))
			return false;
		
		Action other = (Action) o;
		
		return attributeMap.equals(other.attributeMap) && name.equals(other.name);
	}

	public String toString() {
		Set<Entry<String, String>> set = attributeMap.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		
		StringBuilder sb = new StringBuilder(name);
		sb.append(": ");
		
		while(it.hasNext()) {
			sb.append(it.next() + " ");
		}
		
		
		return sb.toString();
	}
}
