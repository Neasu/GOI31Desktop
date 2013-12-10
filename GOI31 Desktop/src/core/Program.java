package core;

import file.LogFile;
import gui.GUIManager;

public class Program implements Runnable {
	
	private boolean isRunning = true;
	private LogFile logf;
	
	
	public void run () {
		
		// Logfile Referenz holen
		logf = LogFile.getRef();
		
		// GUIManager Initialisieren
		new GUIManager().Run();
		
		// Programmloop
		do {
			
			// Schleife verlangsamen
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				logf.functionResult("Thread.sleep", FunctionResult.FAIL, e.toString());
			}
			
		} while (isRunning);
		
		// Logfile schlieﬂen
		logf.closeLogFile();
		
	}
	
}
