package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextFile extends FileSystem {
	
	// Vars
	protected BufferedReader reader = null;
	protected BufferedWriter writer = null;
	
	
	// Constructors
	public TextFile () {
		super ();
	}
	
	public TextFile (String a_path) {
		super (a_path);
		initWriterReader();
	}

	public TextFile (File a_file) {
		super (a_file);
		initWriterReader();
	}
	
	// Methods
	private void initWriterReader () 
	{ 
		try{
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(file));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public BufferedReader getReader () {
		return reader;
	}
	
	public BufferedWriter getWriter () {
		return writer;
	}
	
}
