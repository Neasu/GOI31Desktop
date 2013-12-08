package core;

import gui.GUIManager;

public class Core { 
	
	public static final String version = "0.1";

	public static void main(String[] args) {
		GUIManager manager = new GUIManager();
		
		manager.Run();
	}

}