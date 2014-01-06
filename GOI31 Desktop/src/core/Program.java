package core;

import data.Schedule;
import file.LogFile;
import gui.GUIManager;

public class Program implements Runnable {
	
	private boolean isRunning = true;
	private LogFile logf;
	public Schedule sche;		// TODO Change into only-one-ref
	
	
	public void run () {
		
		// Logfile Referenz holen
		logf = LogFile.getRef();
		
		// Schedule initiieren
		sche = new Schedule ();
		
		// GUIManager Initialisieren
		new GUIManager(this).Run();
		
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
