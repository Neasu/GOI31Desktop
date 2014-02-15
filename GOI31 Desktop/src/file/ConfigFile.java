package file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.LogLevel;

/**
 * 
 * @author Kevin
 * 
 */

public class ConfigFile extends TextFile {

	// Vars
	ArrayList<String> pairList;

	// Constructors
	public ConfigFile() {
		super("config.txt");
		pairList = new ArrayList<String>();

		read();
	}

	// Methods
	public void refresh() {
		try {

			file.delete();
			writer = new BufferedWriter(new FileWriter(file));

			for (String temp : pairList) {
				writer.write(temp);
				writer.newLine();
			}

			writer.flush();

		} catch (IOException ex) {
			LogFile.getRef().textout("The config file couldn't been refreshed! " + ex.getMessage(), LogLevel.ERROR);
		}

	}

	private void read() {
		while (true) {

			try {

				String temp = reader.readLine();

				// System.out.println(temp);

				if (temp == null) {
					break;
				}

				pairList.add(temp);

			} catch (IOException ex) {
				LogFile.getRef().textout("The config file couldn't been read.", LogLevel.WARNING);
			}

		}
	}

	public void addPair(String key, String value) {

		if (exists(key)) {
			remove(key);
		}

		pairList.add(key + ":" + value);

		refresh();
	}

	public void addPair(String key, boolean value) {
		addPair(key, "" + value);
	}

	public void addPair(String key, int value) {
		addPair(key, "" + value);
	}

	public void addPair(String key, double value) {
		addPair(key, "" + value);
	}

	/**
	 * 
	 * @param key
	 * @return "" if not found
	 */
	public String getString(String key) {
		for (String temp : pairList) {
			if (temp.split(":")[0].equals(key)) {
				if (temp.split(":").length > 1) {
					return temp.split(":")[1];
				} else {
					return "";
				}
					
			}
		}

		LogFile.getRef().textout("The Pair with the key: " + key + " couldn't been found.", LogLevel.WARNING);
		return "";
	}

	/**
	 * 
	 * @param key
	 * @return false if not found
	 */
	public boolean getBoolean(String key) {
		String temp = getString(key);

		if (temp.equals("")) {
			return false;
		} else {
			if (temp.equals("true")) {
				return true;
			} else if (temp.equals("false")) {
				return false;
			} else {
				return false;
			}
		}
	}

	public int getInteger(String key) {
		String temp = getString(key);

		return Integer.parseInt(temp);
	}

	public double getDouble(String key) {
		String temp = getString(key);

		return Double.parseDouble(temp);
	}

	public boolean exists(String key) {
		if (getPosition(key) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param key
	 * @return -1 if not found
	 */
	public int getPosition(String key) {

		for (int i = 0; i < pairList.size(); i++) {
			String temp = pairList.get(i);

			if (temp.split(":")[0].equals(key)) {
				return i;
			}
		}

		return -1;
	}

	public void remove(String key) {
		if (exists(key)) {

			pairList.remove(getPosition(key));

		} else {
			LogFile.getRef().textout("The pair with the key: " + key + " couldn't been removed", LogLevel.WARNING);
			return;
		}
	}

	public void closeConfigFile() {
		try {
			reader.close();
			writer.close();
		} catch (Exception e) {
			LogFile.getRef().textout("The config file couldn't been closed", LogLevel.ERROR);
		}

	}
}
