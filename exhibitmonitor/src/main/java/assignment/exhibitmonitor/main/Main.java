package assignment.exhibitmonitor.main;

import java.io.File;
import java.io.IOException;

import assignment.exhibitmonitor.context.ApplicationContext;
import assignment.exhibitmonitor.threadtasks.DatabaseTask;
import assignment.exhibitmonitor.threadtasks.PollerTask;
import assignment.exhibitmonitor.utility.XMLParser;

public class Main {
	
	public static void main(String[] args) throws IOException {
		XMLParser.parseXML();
		String folderToPoll = "D:\\Users\\yichee\\Desktop\\Playground\\Assignment 4\\Watch";
		Thread pollerThread = new Thread(new PollerTask(new File(folderToPoll)));
		
		pollerThread.start();
		
		Thread dbThreadValid = new Thread(new DatabaseTask(ApplicationContext.getValidRecords(), "valid"));
		Thread dbThreadInvalid = new Thread(new DatabaseTask(ApplicationContext.getInvalidRecords(), "invalid"));
		
		dbThreadValid.start();
		dbThreadInvalid.start();
	}
}
