package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import core.LogLevel;
import data.Cooldown;
import file.LogFile;

/**
 * 
 * @author Kevin
 *
 */

public class BroadCastListener implements Runnable {
	
	// Vars
	private int port;
	private String address;
	private DatagramSocket socket;
	private Cooldown cd;
	private ConnectionListener clistener;
	
	// Constructors
	public BroadCastListener (String adr, int prt, int cooldownTime,  ConnectionListener listener) {
		port = prt;
		address = adr;
		cd = new Cooldown(cooldownTime, false);
		clistener =  listener;
	}
	// Methods
	public void run () {
		
		byte[] recieveData = new byte[256];
		recieveData = "false".getBytes();
		
		try {
			socket = new DatagramSocket(port, InetAddress.getByName(address));
		} catch (Exception ex) {
			LogFile.getRef().textout("DatagramSocket couldn't been made. " + ex.getMessage(), LogLevel.ERROR);
			return;
		}
		
		DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
		
		cd.restart();
		
		while (cd.isActive()) {
			
			try {
				socket.receive(recievePacket);
			} catch (IOException e) {
				LogFile.getRef().textout("BroadCast data couldn't been recieved", LogLevel.WARNING);
			}
			
//			try {
//				Thread.sleep(100);
//			} catch (Exception e) {
//				LogFile.getRef().textout("Couldn't sleep!", LogLevel.WARNING);
//			}
			
			if (!new String(recieveData).equals("false")) {
				clistener.incomingConnection(new String (recieveData));
				LogFile.getRef().textout("Conenction found. Recieved Data: " + new String (recieveData), LogLevel.LOG);
			}	
			
			System.out.println(cd.getTimeLeft());
		}
		
		LogFile.getRef().textout("Conenction not found.", LogLevel.LOG);
		
	}
	
}
