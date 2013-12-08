package file;

import java.io.*;

abstract class FileTemplate {

	// Vars
	File file;
	BufferedReader reader;
	BufferedWriter writer;
	
	// Constructor
	FileTemplate ()
	{
		file = null;
	}
	
	FileTemplate (String path)
	{
		file = new File (path);
		initWriterReader();
	}
	
	FileTemplate (File a_file)
	{
		file = a_file;
		initWriterReader();
	}
	
	// Methods
	private void initWriterReader () 
	{ 
		try{
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(file));
		} catch (Exception ex) {ex.printStackTrace();}
	}	
}
