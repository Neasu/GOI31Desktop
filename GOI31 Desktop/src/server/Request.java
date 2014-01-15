package server;

import org.json.JSONObject;

/**
 * Diese Klasse hält Informationen für eine Anfrage an den Server bereit.
 * 
 * Ein Objekt dieser Klasse enthält Informationen zur Art der Anfrage, einen Token der den Benutzer identifiziert und ein JSON Objekt
 * mit Daten, die an den Server übergeben werden sollen.
 * 
 * @author Valentin
 *
 */
public class Request {

	public Request(String _method, String _user, String _pass, JSONObject _data) {
		this.method = _method;
		this.user = _user;
		this.pass = _pass;
		this.data = _data;
	}
	
	
	private String method;
	private String user;
	private String pass;
	private JSONObject data;
	
	
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return the data
	 */
	public JSONObject getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(JSONObject data) {
		this.data = data;
	}
}
