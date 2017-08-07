package assignment.exhibitmonitor.utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLParser {
	
	private static Logger logger = Logger.getLogger("XML Parser");
	private static Document dom;
	
	private XMLParser() {}
	
	public static void parseXML() throws IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//Using factory get an instance of document builder
		DocumentBuilder db = null;
	
		try {
			db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			if (db != null)
				dom = db.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"));
		} catch (SAXException | ParserConfigurationException e) {
			logger.log(Level.FINE, e.getMessage(), e);
		}
	}
}
