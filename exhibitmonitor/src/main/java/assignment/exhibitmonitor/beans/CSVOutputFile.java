package assignment.exhibitmonitor.beans;

import java.util.Date;

public class CSVOutputFile {
	
	private String name;
	private Date time;
	
	public CSVOutputFile(String name, Date time) {
		super();
		this.name = name;
		this.time = time;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}

}
