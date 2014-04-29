package student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import core.LogLevel;
import data.FreeLesson;
import data.ProxyLesson;
import data.Schedule;
import data.SimpleDate;
import file.LogFile;
import gui.GUIManager;
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

	private String user; // Benutzername z.B. schueler@cmb-gt.de
	private String pass; // Passwort (geheim) :D
	private String firstname; // Vorname z.B. Thomas
	private String lastname; // Nachname z.B. Müller
	private String grade; // Jahrgangsstufe z.B. GY31
	private boolean loggedIn = false; // Schon eingeloggt?

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
		LogFile.getRef().textout("Trying to login...", LogLevel.LOG);
		// LogFile.getRef().textout("Username: " + this.user, LogLevel.INFO);
		// LogFile.getRef().textout("Password: " + this.pass, LogLevel.INFO);

		Response response = ServerExecutor.ExecuteRequest(request);

		boolean responseValid = (response.getStatus() == 200);

		if (response.getStatus() == 410) {
			throw new ApiServerException("Sorry, der Dienst wird nicht länger zur Verfügung gestellt :(");
		}

		if (!responseValid) {
			throw new ApiServerException("Fehler beim Einloggen! Überprüfe bitte deine Zugangsdaten! (Error Code " + Integer.toString(response.getStatus()) + ")");
		}

		this.loggedIn = responseValid;

		return responseValid;
	}

	/**
	 * Füllt das Benutzerprofil mit Daten, indem es diese vom Server
	 * herunterlädt.
	 * 
	 * @throws Exception
	 */
	public void populateProfile() throws Exception {
		// Wenn noch nicht eingeloggt, einloggen!
		if (!this.loggedIn) {
			LogFile.getRef().textout("Populate: User is not logged in! Try to log him in...", LogLevel.LOG);
			// Falls das fehl schlägt die Methode verlassen
			if (!this.login()) {
				throw new ApiServerException("Konnte Benutzer nicht anmelden!");
			}
		}

		// Neues Request Objekt anlegen
		Request req = new Request("userinfo", this.user, this.pass, new JSONObject());

		// Weitere Daten werden nicht benötigt also abschicken
		Response resp = ServerExecutor.ExecuteRequest(req);

		if (resp.getStatus() == 200) {
			JSONObject data = resp.getData();

			// Persönliche Daten
			this.firstname = data.getString("firstname");
			this.lastname = data.getString("lastname");
			this.grade = data.getString("class");
			
			// Belegte Kurse
			JSONArray courseArray = data.getJSONArray("courses");
			for (int i = 0; i < courseArray.length(); i++) {
				JSONObject courseObject = courseArray.getJSONObject(i);
				
				Course c = new Course(courseObject.getString("identifier"), courseObject.getString("name"));
				
				JSONArray occurencesArray = courseObject.getJSONArray("occurrences");
				
				for (int j = 0; j < occurencesArray.length(); j++) {
					JSONArray occurence = occurencesArray.getJSONArray(j);
					
					c.addOccurrence(new Occurrence(occurence.getInt(0), occurence.getInt(1), occurence.getString(2), occurence.getString(3)));
				}
				
				this.courses.add(c);
			}
		} else {
			throw new ApiServerException("Fehler! Status Code ist nicht ok (200). Status Code ist " + resp.getStatus());
		}
	}

	/**
	 * Trägt alle belegten Kurse in die Tabelle ein
	 * @throws Exception 
	 */
	public void populateTimetable() throws Exception {
		LogFile.getRef().textout("Timetable wird aktualisiert...", LogLevel.LOG);
		
		Schedule schedule = GUIManager.getProg().getSche();
		
		for (Course c : this.courses) {
			String name = c.getFullname();
			
			for (Occurrence o : c.getOccurrences()) {
				schedule.addNormalLesson(o.getDay() - 1, o.getLesson() - 1, name, o.getTeacher(), o.getRoom());
			}
		}
	}

	public JSONArray getNews() throws ApiServerException {
		// Neues Request Objekt anlegen
		Request req = new Request("news", this.user, this.pass, new JSONObject());

		// Weitere Daten werden nicht benötigt also abschicken
		Response resp = ServerExecutor.ExecuteRequest(req);

		if (resp.getStatus() == 200) {
			JSONObject data = resp.getData();

			return data.getJSONArray("news");
		} else {
			throw new ApiServerException("Fehler! Status Code ist nicht ok (200). Status Code ist " + resp.getStatus());
		}
	}

	/**
	 * Getter for this.firstname
	 * 
	 * @return
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 * Getter for this.lastname
	 * 
	 * @return
	 */
	public String getLastname() {
		return this.lastname;
	}
}
