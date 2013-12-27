package student;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import server.Request;
import server.Response;
import server.Server;

/**
 * Diese Klasse bildet das Profil eines Schülers ab.
 * 
 * @author Valentin
 *
 */
public class Profile {
	
	public Profile(String _user, String _pass) {
		this.user = _user;
		this.pass = _pass;
	}
	
	private String user; 				// Benutzername z.B. tmueller
	private String pass; 				// Passwort (geheim) :D
	private String firstname; 			// Vorname z.B. Thomas
	private String lastname; 			// Nachname z.B. Müller
	private String grade; 				// Jahrgangsstufe z.B. GY31
	private boolean loggedIn = false; 	// Schon eingeloggt?
	private String token;
	
	private List<Course> courses = new ArrayList<Course>();
	
	/**
	 * Diese Methode wird zum einloggen auf dem Server verwendet.
	 * 
	 * @return Gibt true zurück wenn das Login erfolreich war.
	 */
	public boolean login() {
		Request request = new Request("login", null, new JSONObject());
		
		request.getData().put("user", this.user);
		request.getData().put("pass", this.pass);
		
		Response response = Server.ExecuteRequest(request);
		
		boolean responseValid = (response.getStatus() == 200);
		
		if (!responseValid) {
			JOptionPane.showMessageDialog(null, "Fehler beim Einloggen! Überprüfe bitte deine Zugangsdaten! (Error Code " + Integer.toString(response.getStatus()) + ")", "Fehler beim Einloggen!", JOptionPane.ERROR_MESSAGE);
		}
		
		this.loggedIn = responseValid;
		
		return responseValid;
	}
	
	/**
	 * Füllt das Benutzerprofil mit Daten, indem es diese vom Server herunterlädt.
	 */
	public void populateProfile() {
		// Wenn noch nicht eingeloggt, einloggen!
		if(!this.loggedIn) {
			// Falls das fehl schlägt die Methode verlassen
			if (!this.login()) {
				return;
			}
		}
		
		// Neues Request Objekt anlegen
		Request req = new Request("userdata", token, new JSONObject());
		
		// Weitere Daten werden nicht benötigt also abschicken
		Response resp = Server.ExecuteRequest(req);
		
		if (resp.getStatus() == 200) {
			JSONObject data = resp.getData();
			
			this.firstname = data.getString("firstname");
			this.lastname = data.getString("lastname");
			this.grade = data.getString("grade");
		} else {
			
		}
	}
}
