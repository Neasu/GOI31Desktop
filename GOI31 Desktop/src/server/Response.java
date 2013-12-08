package server;

import org.json.JSONObject;

/**
 * Diese Klasse enthält Informationen einer Rückgabe des Servers.
 * 
 * Ein Objekt dieser Klasse enthält die Art der Anfrage, eine Zahl die den Status der Antwort beschreibt und ein JSON Objekt
 * mit Daten die vom Server mitgegeben wurden.
 * 
 * @author Valentin
 *
 */
public class Response {

	public Response(String _method, int _status, JSONObject _data) {
		this.method = _method;
		this.status = _status;
		this.data = _data;
	}
	
	
	private String method;
	private int status;
	private JSONObject data;
	private String plainResponse;
	
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public JSONObject getData() {
		return data;
	}
	
	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getPlainResponse() {
		return plainResponse;
	}

	public void setPlainResponse(String plainResponse) {
		this.plainResponse = plainResponse;
	}
}
