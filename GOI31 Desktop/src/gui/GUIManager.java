package gui;

// Lib Imports
import java.util.*;

import javax.swing.UIManager;

import core.FunctionResult;
import core.Program;
import file.LogFile;

public class GUIManager {
	
	ArrayList<Screen> screenList;
	static Program prog;
	
	// Constructors
	public GUIManager(Program prog) {
		this.prog = prog;
	}
	
	public void Run ()
	{
		screenList = new ArrayList<Screen> ();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			LogFile.getRef().functionResult("Set System Look and Feel", FunctionResult.FAIL);
		}
		
		
		screenList.add (new LogoScreen());
		screenList.add (new MainScreen());
		
		screenList.get(0).Init();
		screenList.get(1).Init();
	}
	
}


