package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Server {
	public static Response ExecuteRequest(Request request) {
		// Rückgabe Objekt schon mal erzeugen
		Response resp = new Response(request.getMethod(), 500, new JSONObject());
		
		JSONObject bodyObject = new JSONObject();
		
		bodyObject.put("method", request.getMethod());
		bodyObject.put("token", request.getToken());
		bodyObject.put("data", request.getData());
		
		try {
			URL url = new URL("https://www.ssl-id.de/apps.valentingerlach.de/gy31/api.php");
			HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			//HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
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
			
			resp.setPlainResponse(response.toString());
			JSONObject responseObject = new JSONObject(resp.getPlainResponse());
			
			resp.setStatus(responseObject.getInt("status"));
			resp.setData(responseObject.getJSONObject("data"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error while fetching data!", JOptionPane.PLAIN_MESSAGE);
		}
		
		// Objekt zurückgeben
		return resp;
	}
}
