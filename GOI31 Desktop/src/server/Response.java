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

	public Response(String _method, int _status, String _msg, JSONObject _data, String _plainResponse) {
		this.method = _method;
		this.status = _status;
		this.msg = _msg;
		this.data = _data;
		this.plainResponse = _plainResponse;
	}
	
	
	private String method;
	private int status;
	private String msg;
	private JSONObject data;
	private String plainResponse;
	
	
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
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
	/**
	 * @return the plainResponse
	 */
	public String getPlainResponse() {
		return plainResponse;
	}
	/**
	 * @param plainResponse the plainResponse to set
	 */
	public void setPlainResponse(String plainResponse) {
		this.plainResponse = plainResponse;
	}
	
}
