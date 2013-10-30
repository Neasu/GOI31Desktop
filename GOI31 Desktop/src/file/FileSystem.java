package file;

import java.awt.*;
import java.io.*;

public class FileSystem {
	
	public static Image getImage (String path, boolean fromJAR) {
		Image img = null;
		
			if (fromJAR) {
				img = Toolkit.getDefaultToolkit().getImage(FileSystem.class.getResource(path));
			} else {
				img = Toolkit.getDefaultToolkit().getImage(path);
			}
			
		return img;
	}
	
	public static BufferedReader getReader (String path, boolean fromJAR)
	{
		BufferedReader reader = null;
		try {
			if (fromJAR)
			{
				reader = new BufferedReader(new InputStreamReader(FileSystem.class.getResourceAsStream(path)));
			} else {
				reader = new BufferedReader(new FileReader (new File (path)));
			}
		} catch (Exception ex) {ex.printStackTrace();}
		return reader;
	}
	
	public static BufferedWriter getWriter (String path, boolean fromJAR) {
		BufferedWriter writer = null;
		
		try {
			
			if (fromJAR)
			{
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(FileSystem.class.getResource(path).getFile()))));
			} else {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File (path))));
			}
			
		} catch (Exception ex) {ex.printStackTrace();}
		
		return writer;
	}
	
}
