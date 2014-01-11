package gui;

// Lib Imports
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import core.FunctionResult;
import core.LogLevel;
import core.Program;
import file.LogFile;

public class GUIManager {
	
	// Vars
	private static Program prog;
	
	private Screen logoScreen = null;
	private Screen logonScreen = null;
	private Screen mainScreen = null;
	private WindowAdapter windowListener;
	
	// Constructors
	public GUIManager(Program prog) {
		GUIManager.prog = prog;
		windowListener = new ScreenListener();
		LogFile.getRef().textout("GUIManager has been initialized.", LogLevel.LOG);
	}
	
	public void run ()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			LogFile.getRef().functionResult("Set System Look and Feel", FunctionResult.FAIL);
		}
		
//		getLogoScreen  ();
		getLogonScreen ();
	}
	
	public Screen getMainScreen () {
		if (mainScreen == null) {
			mainScreen = new MainScreen (this);
			mainScreen.getFrame().addWindowListener(windowListener);
			return mainScreen;
		} else {
			return mainScreen;
		}
	}
	
	public Screen getLogoScreen () {
		if (logoScreen == null) {
			logoScreen = new LogoScreen (this);
			logoScreen.getFrame().addWindowListener(windowListener);
			return logoScreen;
		} else {
			return logoScreen;
		}
	}

	public Screen getLogonScreen () {
		if (logonScreen == null) {
			logonScreen = new LogonScreen (this);
			logonScreen.getFrame().addWindowListener(windowListener);
			return logonScreen;
		} else {
			return logonScreen;
		}
	}
	
	// Use with Caution
	private void closeScreen (Screen screen) {
		 WindowEvent wev = new WindowEvent(screen.getFrame(), WindowEvent.WINDOW_CLOSING);
         Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
         LogFile.getRef().textout("Screen: " + screen.getScreenName() + " has been closed.", LogLevel.LOG);
	}
	
	public void closeMainScreen () {
		if (mainScreen != null) {
			closeScreen(mainScreen);
		}
	}
	
	public void closeLogoScreen () {
		if (logoScreen != null) {
			closeScreen(logoScreen);
		}
	}
	
	public void closeLogonScreen () {
		if (logonScreen != null) {
			closeScreen(logonScreen);
		}
	}
	
	public static Program getProg() {
		return prog;
	}
	
//	public Screen getScreenByID (int screenID) {
//		for (int i = 0; i < screenList.size(); i++) {
//			if (screenList.get(i).screenID == screenID)
//				return screenList.get(i);
//		}
//		System.out.println("Screen with the ID: " + screenID + " wasn't found!");
//		return null;
//	}
	
	// Schließen des Programms abfangen
	public class ScreenListener extends WindowAdapter {
		public void windowClosing (WindowEvent we) {
			LogFile.getRef().textout("Programm is terminating.", LogLevel.LOG);
			
			// Logfile schließen
			LogFile.getRef().closeLogFile();
		}
	}
	
}


