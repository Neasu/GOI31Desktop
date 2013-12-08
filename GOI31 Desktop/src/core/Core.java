package core;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONObject;

import server.Request;
import server.Response;
import server.ServerExecutor;

import gui.GUIManager;

public class Core {

        public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
                // TODO Auto-generated method stub
                Request req = new Request("login", "login", new JSONObject());
                
                Response resp = ServerExecutor.ExecuteRequest(req);
                
                JOptionPane.showMessageDialog(null, resp.getPlainResponse(), "Information", JOptionPane.PLAIN_MESSAGE);
        
				// N3asu
				
				new GUIManager ().Run ();
		}

}