package core;

import javax.swing.UnsupportedLookAndFeelException;

public class Core {
	
	public static final String  VERSION				= "0.1";
	public static final String  NAME 				= "GOI31 Desktop";
	public static final boolean DEBUG 				= true;

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		
//		Request req = new Request("login", "login", new JSONObject());
//
//		Response resp = ServerExecutor.ExecuteRequest(req);
//
//		JOptionPane.showMessageDialog(null, resp.getPlainResponse(),
//				"Information", JOptionPane.PLAIN_MESSAGE);
		
		Runnable threadJob = new Program ();
		
		Thread mainThread = new Thread (threadJob);
		
		mainThread.start ();
		
	}

}