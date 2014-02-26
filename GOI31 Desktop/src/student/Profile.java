package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import core.LogLevel;
import data.FreeLesson;
import data.ProxyLesson;
import data.SimpleDate;
import file.LogFile;
import server.ApiServerException;
import server.Request;
import server.Response;
import server.ServerExecutor;

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
	
	private List<Course> courses = new ArrayList<Course>();
	
	/**
	 * Diese Methode wird zum einloggen auf dem Server verwendet.
	 * 
	 * @return Gibt true zurück wenn das Login erfolreich war.
	 * @throws Exception 
	 */
	public boolean login() throws Exception {
		Request request = new Request("ping", this.user, this.pass, new JSONObject());
		
		// Logging FTW
		LogFile.getRef().textout("Trying to login...", LogLevel.INFO);
		LogFile.getRef().textout("Username: " + this.user, LogLevel.INFO);
		LogFile.getRef().textout("Password: " + this.pass, LogLevel.INFO);
		
		Response response = ServerExecutor.ExecuteRequest(request);
		
		boolean responseValid = (response.getStatus() == 200);
		
		if (!responseValid) {
			throw new ApiServerException("Fehler beim Einloggen! Überprüfe bitte deine Zugangsdaten! (Error Code " + Integer.toString(response.getStatus()) + ")");
		}
		
		this.loggedIn = responseValid;
		
		return responseValid;
	}
	
	/**
	 * Füllt das Benutzerprofil mit Daten, indem es diese vom Server herunterlädt.
	 * @throws Exception 
	 */
	public void populateProfile() throws Exception {
		// Wenn noch nicht eingeloggt, einloggen!
		if(!this.loggedIn) {
			// Falls das fehl schlägt die Methode verlassen
			if (!this.login()) {
				
			}
		}
		
		// Neues Request Objekt anlegen
		Request req = new Request("userinfo", this.user, this.pass, new JSONObject());
		
		// Weitere Daten werden nicht benötigt also abschicken
		Response resp = ServerExecutor.ExecuteRequest(req);
		
		if (resp.getStatus() == 200) {
			JSONObject data = resp.getData();
			
			this.firstname = data.getString("firstname");
			this.lastname = data.getString("lastname");
			this.grade = data.getString("class");
		} else {
			throw new ApiServerException("Fehler! Status Code ist nicht ok (200). Status Code ist " + resp.getStatus());
		}
	}
	
<<<<<<< HEAD
	/**
	 * Holt die Information zum Stundenplan von DSB
	 * @throws IOException
	 */
	public void populateTimetable() throws IOException {
		LogFile.getRef().textout("Timetable wird aktualisiert...", LogLevel.INFO);
		
		// Dokument laden
		Document doc = Jsoup.connect("https://light.dsbcontrol.de/DSBlightWebsite/Data/e48cf7a5-9bb4-4ed6-8944-2dd568f1c83b/e9e94d23-92de-417b-af5b-88e17c02f7d3/e9e94d23-92de-417b-af5b-88e17c02f7d3.htm").get();
		
		// Alle Tabellen holen
		Elements tables = doc.select("table");
		
		// Hier werden alle Daten gespeichert
		HashMap<SimpleDate, FreeLesson> freeLessons = new HashMap<SimpleDate, FreeLesson>();
		HashMap<SimpleDate, ProxyLesson> proxyLessons = new HashMap<SimpleDate, ProxyLesson>();
		
		for (Element element : tables) {
			if (element.hasClass("Ich komme nicht weiter, ich will API Key!!!!!!!!!!!!!!!!")) {
				
			}
		}
	}
=======
	
>>>>>>> 0ff943ca813ecd117375b908935784ee28b25c9f
}
