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

	public Request(String _method, String _token, JSONObject _data) {
		this.method = _method;
		this.token = _token;
		this.data = _data;
	}
	
	
	private String method;
	private String token;
	private JSONObject data;
	
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public JSONObject getData() {
		return data;
	}
	
	public void setData(JSONObject data) {
		this.data = data;
	}
	
}
