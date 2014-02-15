package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import core.LogLevel;

/**
 * 
 * @author Kevin
 *
 */

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
	protected void initWriterReader () 
	{ 
		try{
			file.createNewFile();
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(file, true));
		} catch (Exception ex) {
			LogFile.getRef().textout("Failed to initialize Reader and Writer: " + ex.getMessage(), LogLevel.ERROR);
		}
	}
	
	
	public BufferedReader getReader () {
		return reader;
	}
	
	public BufferedWriter getWriter () {
		return writer;
	}
	
}
