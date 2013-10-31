package file;

import java.io.File;
import java.io.IOException;

public class VersionFile extends FileTemplate {

	// Vars
	private float version;
	private String input;
	
	// Constructors
	public VersionFile ()
	{
		super ("version.txt");
		Init ();
	}
	
	public VersionFile (String path)
	{
		super (path);
		Init ();
	}
	
	public VersionFile (File a_file)
	{
		super (a_file);
		Init ();
	}
	
	// Methods
	protected void Init ()
	{
		try {
			input = reader.readLine();
			String[] splitted = input.split(" ");
			version = Float.parseFloat(splitted[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public float getVersion ()
	{
		return version;
	}
	
	public String getVersionText ()
	{
		return input;
	}
	
}
