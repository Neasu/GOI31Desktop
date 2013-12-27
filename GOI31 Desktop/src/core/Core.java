package core;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONObject;

import server.Request;
import server.Response;
import server.Server;

public class Core {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Request req = new Request("login", "login", new JSONObject());
		
		Response resp = Server.ExecuteRequest(req);
		
		JOptionPane.showMessageDialog(null, resp.getPlainResponse(), "Information", JOptionPane.PLAIN_MESSAGE);
	}

}
