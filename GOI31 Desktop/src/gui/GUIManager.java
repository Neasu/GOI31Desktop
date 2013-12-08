package gui;

// Lib Imports
import java.util.*;


public class GUIManager {
	
	ArrayList<Screen> screenList;
	
	public void Run ()
	{
//		screenList = new ArrayList<Screen> ();
//		
//		screenList.add (new LogoScreen());
//		
//		screenList.get(0).Init();
		
		BaukastenMainGUI mgui = new BaukastenMainGUI ();
		
		mgui.Init ();
	}
	
}


