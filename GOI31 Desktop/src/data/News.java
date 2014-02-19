package data;

import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Kevin
 *
 */
public class News {
	
	// Vars
	private JTextArea newsArea;
	private boolean first = true;
	
	// Constructors
	public News (JTextArea textA) {
		newsArea = textA;
	}
	
	// Methods
	public void addNews (JSONObject json) {
		
		JSONArray jarray = json.getJSONArray("news");
		JSONObject jobj;
		
		for (int i = 0; i < jarray.length(); i++) {
			
			jobj = jarray.getJSONObject(i);
			
			addNews (jobj.getString("title"), jobj.getString("author"), jobj.getString("date"), jobj.getString("content"));
			
		}
	}
	
	public void addNews (String title, String author, String date, String content) {
		
		String temp = "";
		
		if (first) {
			temp += "================================================================\n";
			first = false;
		}
		
		temp += title + " - " + date + "\n\n";
		temp += content + "\n\n";
		temp += "Autor: " + author + "\n";
		
		temp += "================================================================\n";
		
		newsArea.append(temp);
	}
	
	public void setNews (JSONObject json) {
		newsArea.setText ("");
		addNews(json);
	}
	
}
