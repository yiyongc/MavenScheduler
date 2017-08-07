package assignment.exhibitmonitor.main;

import java.io.IOException;

import assignment.exhibitmonitor.threadtasks.PollerTask;
import assignment.exhibitmonitor.utility.XMLParser;

public class Main {
	
	public static void main(String[] args) throws IOException {
		XMLParser.parseXML();
		
		Thread pollerThread = new Thread(new PollerTask());
		
		pollerThread.start();
	}
}
