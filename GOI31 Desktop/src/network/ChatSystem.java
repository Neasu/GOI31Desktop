package network;

public class ChatSystem {
	
	// Vars
	private static final String connectionAddress = "233.0.113.0";
	private static final String chatAddress = "233.0.114.0";
	
	// Constructors
	
	// Methods
	public void start () {
		MulticastServer server = new MulticastServer(connectionAddress, 5001);
		server.send("HALLO");
		server.close();
		
	}
	
}
