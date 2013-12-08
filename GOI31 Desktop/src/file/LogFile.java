package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;

// Diese Klasse kann nur ein einziges mal erzeugt werden
// Logfile File-Name -> Logfile_tt.mm.jj.html

public class LogFile extends TextFile {
	
	// Vars
	private static LogFile ref;
	
	// Constructor
	private LogFile () {
		super ();
		createLogfile();
	}
	
	// Methods
	public static LogFile getRef () {
		if (ref == null) {
			ref = new LogFile ();
			return ref;
		} else {
			return ref;
		}
	}
	
	private void createLogfile () {
		
	}
	
	public BufferedReader getReader () {
		return null;
	}
	
	public BufferedWriter getWriter () {
		return null;
	}

}
