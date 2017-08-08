package assignment.exhibitmonitor.utility;

import java.util.TimerTask;

import assignment.exhibitmonitor.threadtasks.ExportTask;

public class OutputTimerTask extends TimerTask {

	private String fileName;
	private String folder;
	
	public OutputTimerTask(String file, String folder) {
		this.fileName = file;
		this.folder = folder;
	}
	
	@Override
	public void run() {

		Thread outputThread = new Thread(new ExportTask(fileName, folder));
		outputThread.start();
		
	}

}
