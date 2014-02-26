package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import core.LogLevel;
import file.LogFile;

public class MulticastServer {
	
	// Vars
	private String addresse;
	private int port;
	private InetAddress group;
	private byte[] buffer;
	private DatagramPacket packet;
	private DatagramSocket socket;
	
	// Constructors
	public MulticastServer (String addr, int prt) {
		addresse = addr;
		port = prt;
		buffer = new byte[256];
		
		try {
			group = InetAddress.getByName(addresse);
			socket = new DatagramSocket(port);
		} catch (Exception e) {
			LogFile.getRef().textout("Couldn't make MulticastServer due to: " + e.getMessage(), LogLevel.WARNING);
		}
	}
	
	// Methods
	public void send (String msg) {
		
		buffer = msg.getBytes();
		
		packet = new DatagramPacket(buffer, buffer.length, group, port);
		
		try {
			socket.send(packet);
		} catch (Exception e) {
			LogFile.getRef().textout("Couldn't send message due to: " + e.getMessage(), LogLevel.WARNING);
		}
		
	}
	
	public void close () {
		socket.close();
	}
	
	public static boolean isIPAddress (String addr) {
		try {
			Integer.getInteger(addr.split("\\.")[0]);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
}
