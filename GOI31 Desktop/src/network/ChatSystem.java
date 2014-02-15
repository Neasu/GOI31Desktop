package network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 
 * @author Kevin
 *
 */

public class ChatSystem {
	// Vars
	InetAddress localHost;
	NetworkInterface networkInterface;
	ArrayList<InterfaceAddress> addresses;
	ArrayList<BroadCastListener> connections;
	
	public static final int PORT = 5001;
	
	
	// Constructors
	public ChatSystem () {
		try{
			localHost = Inet4Address.getLocalHost();
			networkInterface = NetworkInterface.getByInetAddress(localHost);
		} catch (SocketException ex) {
			
		} catch (UnknownHostException ex) {
			
		}
		
		addresses = new ArrayList<InterfaceAddress>();
		connections = new ArrayList<BroadCastListener>();
		
		for (InterfaceAddress address : networkInterface.getInterfaceAddresses()) {
			if (address.getBroadcast() != null) {
				addresses.add (address);
			}
		}
		
	}
	
	// Methods
	public void start () {
		for (InterfaceAddress address : addresses) {
			BroadCastListener con = new BroadCastListener(address.getAddress().getHostAddress(), PORT, 30, new connlistener());

			Thread thread = new Thread (con);
			thread.start();
			
			connections.add(con);
		}
	}
	
	// Intern Classes
	public class connlistener implements ConnectionListener {
		@Override
		public void incomingConnection(String msg) {
			System.out.println(msg);
			
		}
	}
	
}
