package file;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import core.Core;
import core.FunctionResult;
import data.TimePair;
import core.LogLevel;

public class LogFile extends TextFile {

	// Vars
	private static LogFile ref;
	private Calendar cal = Calendar.getInstance ();

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
		
		if (!Core.WRITELOGFILE) {
			return;
		}
		
		String tempFileName = TimePair.formatDate(cal) + "_" + TimePair.formatTime(cal) + "_" + "Logfile.html";
		tempFileName = tempFileName.replace(':', '.');
		
		file = new File(tempFileName);

		try {
			if (file.isFile()) {
				file.delete();
			}

			file.createNewFile();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		initWriterReader();

		textout("<html><head><title>Logfile</title></head>");
		textout("<body><font face='courier new'>");
		writeTopic("Logfile", 3);

//		// Link für E-Mail
//		textout("<a href='mailto:kevin.sieverding@gmail.com?subject=Logfile'>");
//		textout("Send E-Mail to me</a><br><br>");
	}

	public void closeLogFile() {

		if (!Core.WRITELOGFILE) {
			return;
		}
		
		textout("<br><br>End of Logfile</font></body></html>");

		try {
			reader.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void writeTopic (String topic, int size) {
		
		if (!Core.WRITELOGFILE) {
			return;
		}
		
		textout  ("<table cellspacing='0' cellpadding='0' width='100%%' ");
		textout  ("bgcolor='#DFDFE5'>\n<tr>\n<td>\n<font face='arial' ");
		textout  ("size='+" + size + "'>\n");
		textout  (topic);
		textout  ("</font>\n</td>\n</tr>\n</table>\n<br>");
		
	}
	
	private void textout (String text) {

		if (!Core.WRITELOGFILE) {
			return;
		}
		
		try {
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void textout (String text, LogLevel logLevel) {
		
		if (logLevel.ordinal() < Core.LOGLEVEL.ordinal() || !Core.WRITELOGFILE) {
			return;
		}

		String temp = "";
		
		// Zeit
		temp += "[" + TimePair.formatDate(cal) + " " + TimePair.formatTime(cal) + "]";
		
		// LogLevel
		temp += "[";
		
		if (logLevel == LogLevel.ERROR) {
			temp += "<font color=red>ERR</font>";
		} else if (logLevel == LogLevel.WARNING) {
			temp += "<font color=yellow>WRN</font>";
		} else if (logLevel == LogLevel.LOG) {
			temp += "<font color=blue>LOG</font>";
		} else if (logLevel == LogLevel.INFO) {
			temp += "<font color=lightblue>INF</font>";
		}
		
		temp += "]";
		temp += " ";
		
		temp += text;
		
		temp += "<br>";
		
		textout (temp);
	}
	
	public void functionResult (String name, FunctionResult result) {
		functionResult(name, result, "-/-");
	}
	
	public void functionResult (String name, FunctionResult result, String text) {
		
		if (result == FunctionResult.OK)
	    {
	        textout ("Function succeeded: " + name + " | " + text, LogLevel.LOG);
	    }
	    else
	    {
	    	textout ("Function failed: " + name + " | " + text, LogLevel.ERROR);
	    }
		
	}

}
