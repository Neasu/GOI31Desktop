package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import core.FunctionResult;

// Diese Klasse kann nur ein einziges mal erzeugt werden
// Logfile File-Name -> Logfile.html

public class LogFile extends TextFile {

	// Vars
	private static LogFile ref;

	// Constructor
	private LogFile() {
		super();
		createLogfile();
	}

	// Methods
	public static LogFile getRef() {
		if (ref == null) {
			ref = new LogFile();
			return ref;
		} else {
			return ref;
		}
	}

	private void createLogfile() {
		file = new File ("Logfile.html");
		
		try {
			if (file.isFile()) {
				file.delete ();
			}
			
			file.createNewFile();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		initWriterReader();
		
		textout ("<html><head><title>Logfile</title></head>");
	    textout ("<body><font face='courier new'>");
	    writeTopic ("Logfile", 3);
	    
	    // Link für E-Mail
	    textout ("<a href='mailto:kevin.sieverding@gmail.com?subject=Logfile'>");
	    textout ("Send E-Mail to me</a><br><br>");
	}

	public void closeLogFile () {
		
		textout ("<br><br>End of Logfile</font></body></html>");
		
		try {
			reader.close ();
			writer.close ();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void textout (String text) {

		try {
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void textout (Colors col, String text) {
		textout (col, false, text);
	}

	public void textout (Colors col, boolean list, String text) {

		// Listen-Tag
		if (list == true) {
			textout("<li>");
		}

		// Farb-Tag
		switch (col) {

		case BLACK: {
			textout("<font color=black>");
			break;
		}
		
		case RED: {
			textout ("<font color=red>");
			break;
		}
		
		case GREEN: {
			textout ("<font color=green>");
			break;
		}
		
		case BLUE: {
			textout ("<font color=blue>");
			break;
		}
		
		case PURPLE: {
			textout ("<font color=purple>");
			break;
		}

		}
		
		textout (text);
		textout ("</font>");
		
		if (list == false) {
			textout ("<br>");
		} else {
			textout ("</li>");
		}
	}

	public void writeTopic (String topic, int size) {
		
		textout  ("<table cellspacing='0' cellpadding='0' width='100%%' ");
		textout  ("bgcolor='#DFDFE5'>\n<tr>\n<td>\n<font face='arial' ");
		textout  ("size='+" + size + "'>\n");
		textout  (topic);
		textout  ("</font>\n</td>\n</tr>\n</table>\n<br>");
		
	}
	
	public void functionResult (String name, FunctionResult result) {
		functionResult(name, result, "-/-");
	}
	
	public void functionResult (String name, FunctionResult result, String text) {
		
		if (result == FunctionResult.OK)
	    {
	        textout  ("<table width='100%%' cellspacing='1' cellpadding='5'");
	        textout  (" border='0' bgcolor='#C0C0C0'><tr><td bgcolor=");
	        textout  ("'#FFFFFF' width='35%'>" + name + "</TD>");
	        textout  ("<td bgcolor='#FFFFFF' width='30%%'><font color=");
	        textout  ("'green'>OK</FONT></TD><td bgcolor='#FFFFFF' ");
	        textout  ("width='35%%'>" + text + "</TD></tr></table>");
	    }
	    else
	    {
	    	textout  ("<table width='100%%' cellspacing='1' cellpadding='5'");
	        textout  (" border='0' bgcolor='#C0C0C0'><tr><td bgcolor=");
	        textout  ("'#FFFFFF' width='35%'>" + name + "</TD>");
	        textout  ("<td bgcolor='#FFFFFF' width='30%%'><font color=");
	        textout  ("'red'>ERROR</FONT></TD><td bgcolor='#FFFFFF' ");
	        textout  ("width='35%%'>-/-</TD></tr></table>");
	    }
		
	}
	
	public BufferedReader getReader() {
		return null;
	}

	public BufferedWriter getWriter() {
		return null;
	}

	// Enum mit den Farben
	public enum Colors {
		BLACK, RED, GREEN, BLUE, PURPLE,
	}

}
