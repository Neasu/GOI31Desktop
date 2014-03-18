package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import core.LogLevel;
import file.LogFile;

public class ServerExecutor {
	public static Response ExecuteRequest(Request request) throws ApiServerException {
		// Rückgabe Objekt schon mal erzeugen
		Response resp = new Response(request.getMethod(), 999, "Unknown Error", null, null);
		
		JSONObject bodyObject = new JSONObject();
		
		bodyObject.put("method", request.getMethod());
		bodyObject.put("user", request.getUser());
		bodyObject.put("password", request.getPass());
		bodyObject.put("data", request.getData());
		
		try {
			
			
			URL url = new URL("http://diskstation.valentingerlach.de/apis/goi31/api.php");
			//HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "GY31 Desktop Client");
			
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			
			// Erster Ansatz
			//String body = "json=" + URLEncoder.encode(bodyObject.toString(2), "UTF-8");
			
			// Zweiter Ansatz (besser)
			String body = bodyObject.toString(2);
			
			// Erster Ansatz
			//con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			// Zweiter Ansatz (besser)
			con.setRequestProperty("Content-Type", "application/json");
			
			con.setRequestProperty("Content-Length", Integer.toString(body.length()));
			
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write(body);
			writer.flush();
			writer.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String input;
			StringBuffer response = new StringBuffer();
			
			while ((input = reader.readLine()) != null) {
				response.append(input);
			}
			
			reader.close();
			
			LogFile.getRef().textout(response.toString(), LogLevel.LOG);
			
			resp.setPlainResponse(response.toString());
			JSONObject responseObject = new JSONObject(resp.getPlainResponse());
			
			resp.setStatus(responseObject.getInt("status"));
			
			try {
				resp.setData(responseObject.getJSONObject("data"));
			} catch (Exception e) {
				LogFile.getRef().textout("No data object specified! Using default...", LogLevel.ERROR);
				resp.setData(new JSONObject());
			}
		} catch (IOException e) {
			throw new ApiServerException("Konnte keine Verbindung mit dem API Server herstellen! Bitte überprüfe deine Internetverbindung!");
		} catch (JSONException e) {
			throw new ApiServerException("Konnte JSON Objekt nicht parsen! Bitte befolge die Anweisungen auf www.goi31app.de/report-bug");
		} catch (Exception e) {
			throw e;
			//throw new ApiServerException("Unerwarteter Fehler! Bitte befolge die Anweisungen auf www.goi31app.de/report-bug");
		}
		
		// Objekt zurückgeben
		return resp;
	}
}
