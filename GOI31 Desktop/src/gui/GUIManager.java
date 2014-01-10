package gui;

// Lib Imports
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import core.FunctionResult;
import core.Program;
import file.LogFile;

public class GUIManager {
	
	// Vars
	public static Program prog;
	
	private Screen logoScreen = null;
	private Screen logonScreen = null;
	private Screen mainScreen = null;

	private static Program prog;

	private Screen logoScreen = null;
	private Screen logonScreen = null;
	private Screen mainScreen = null;

	
	// Constructors
	public GUIManager(Program prog) {
		this.prog = prog;
	}
	
	public void Run ()
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
			return mainScreen;
		} else {
			return mainScreen;
		}
	}
	
	public Screen getLogoScreen () {
		if (logoScreen == null) {
			logoScreen = new LogoScreen (this);
			return logoScreen;
		} else {
			return logoScreen;
		}
	}

	public Screen getLogonScreen () {
		if (logonScreen == null) {
			logonScreen = new LogonScreen (this);
			return logonScreen;
		} else {
			return logonScreen;
		}
	}
	
	// Use with Caution
	private void closeScreen (JFrame frame) {
		 WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
         Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	public void closeMainScreen () {
		if (mainScreen != null) {
			closeScreen(mainScreen.getFrame());
		}
	}
	
	public void closeLogoScreen () {
		if (logoScreen != null) {
			closeScreen(logoScreen.getFrame());
		}
	}
	
	public void closeLogonScreen () {
		if (logonScreen != null) {
			closeScreen(logonScreen.getFrame());
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
	
}


