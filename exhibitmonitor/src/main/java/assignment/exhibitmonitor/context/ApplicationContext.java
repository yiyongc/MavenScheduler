package assignment.exhibitmonitor.context;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assignment.exhibitmonitor.beans.CSVInputFile;
import assignment.exhibitmonitor.beans.CSVOutputFile;
import assignment.exhibitmonitor.beans.Field;

public class ApplicationContext {
	private ApplicationContext() {}
	
	public static final Map<String, Date> fileLog = new HashMap<>();
	public static final Map<CSVInputFile, List<Field>> inputFiles = new HashMap<>();
	public static final List<CSVOutputFile> outputFiles = new ArrayList<>();
}
