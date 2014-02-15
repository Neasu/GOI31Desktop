package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import core.LogLevel;
import file.LogFile;

/**
 * 
 * @author Kevin
 *
 */

public class Connection implements Runnable {

	// Vars
	private String address;
	private int port;
	private Socket sock;
	private ArrayList<ConnectionListener> listenerList;
	
	private BufferedReader reader;
	
	// Constructors
	public Connection (String addr, int prt, ConnectionListener listener) {
		address = addr;
		port = prt;
		
		listenerList = new ArrayList<ConnectionListener> ();
		addConnectionListener(listener);
	}
	
	// Methods
	public void run () {
		try {
			sock = new Socket (address, port);
			
			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			LogFile.getRef().textout("Listening to " + address.toString() + ":" + port, LogLevel.LOG);
			
		} catch (IOException e) {
			LogFile.getRef().textout("Couldn't make Socket! " + e.getMessage(), LogLevel.ERROR);
		}
		
	}
	
	public void addConnectionListener (ConnectionListener listener) {
		listenerList.add(listener);
	}
	
	public void removeConnectionListener (ConnectionListener listener) {
		if (listenerList.contains(listener)) {
			listenerList.remove(listener);
		} else {
			LogFile.getRef().textout("A ConnectionListener couldn't been removed.", LogLevel.WARNING);
		}
	}
	
}
