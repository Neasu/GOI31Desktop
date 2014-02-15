package core;

import java.util.ArrayList;
import java.util.Calendar;

import network.ChatSystem;
import data.Schedule;
import data.TimePair;
import file.ConfigFile;
import file.LogFile;
import gui.GUIManager;

/**
 * 
 * @author Kevin
 *
 */

public class Program implements Runnable {
	
	private boolean isRunning = true;
	private LogFile logf;
	private boolean isOnline = false;
	private int today = TimePair.getTodayAsInt(Calendar.getInstance());
	
	private String username = "";
	
	private Schedule sche;		// TODO Change into only-one-ref
	private GUIManager guim;
	private static ArrayList<Updateable> updateList;
	private ConfigFile conf;
	
	private ChatSystem chat;
	
	
	public void run () {
		
		// UpdateList initiieren
		updateList = new ArrayList<Updateable> ();
		
		// Logfile Referenz holen
		logf = LogFile.getRef();
		
		logf.textout("Program Main Loop started.", LogLevel.LOG);
		
		// Schedule initiieren
		sche = new Schedule ();
		
		// ConfigFile
		conf  = new ConfigFile ();
		
		// ChatSystem
		chat = new ChatSystem ();
		chat.start();
		
		// GUIManager Initialisieren
		guim = new GUIManager(this);
		guim.run();
		
		// Programmloop
		do {
			
			// Update aufrufen
			for (Updateable upd : updateList) {
				upd.update();
			}
			
			// Ist heute noch der gleiche Tag?
			if (today != TimePair.getTodayAsInt(Calendar.getInstance())) {
				LogFile.getRef().textout("A new Day has begun! Good Morning!", LogLevel.INFO);
				today = TimePair.getTodayAsInt(Calendar.getInstance());
				sche.updateData();
			}
			
			// Schleife verlangsamen
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				logf.functionResult("Thread.sleep", FunctionResult.FAIL, e.toString());
			}
			
		} while (isRunning);
	}
	
	public static void addUpdateable (Updateable upd) {
		updateList.add(upd);
	}
	
	public static void removeUpdateable (Updateable upd) {
		if (updateList.contains(upd)){
			updateList.remove(upd);
		} else {
			LogFile.getRef().textout("The Updateable: " + upd.toString() + " couldn't been removed.", LogLevel.WARNING);
		}
	}
	
	// Getters Setters
	public boolean isRunning() {
		return isRunning;
	}


	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isOnline() {
		return isOnline;
	}


	public Schedule getSche() {
		return sche;
	}


	public GUIManager getGuim() {
		return guim;
	}
	
	public String getUserName () {
		return username;
	}
	
	public void setUserName (String username) {
		this.username = username;
	}
	
	public ConfigFile getConfigFile () {
		return conf;
	}
	
}
