package file;

import java.io.*;

abstract class FileSystem {

	// Vars
	protected File file;
//	protected boolean couldbeloaded = true;
//	protected BufferedReader reader = null;
//	protected BufferedWriter writer = null;
	
	// Constructor
	FileSystem ()
	{
		file = null;
	}
	
	FileSystem (String path)
	{
		file = new File (path);
//		System.out.println(file.getAbsolutePath());
	}
	
	FileSystem (File a_file)
	{
		file = a_file;
	}
	
	// Methods
//	private void initWriterReader () 
//	{ 
//		try{
//			reader = new BufferedReader(new FileReader(file));
//			writer = new BufferedWriter(new FileWriter(file));
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			couldbeloaded = false;
//		}
//	}	
}
