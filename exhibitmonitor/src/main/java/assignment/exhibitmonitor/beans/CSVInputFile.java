package assignment.exhibitmonitor.beans;

import java.util.Date;

public class CSVInputFile {
	
	private String name;
	private Date time;
	private byte gracePeriod;
	
	
	public CSVInputFile(String name, Date time, byte gracePeriod) {
		super();
		this.name = name;
		this.time = time;
		this.gracePeriod = gracePeriod;
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


	public byte getGracePeriod() {
		return gracePeriod;
	}


	public void setGracePeriod(byte gracePeriod) {
		this.gracePeriod = gracePeriod;
	}
	
	
}
