package data;

/**
 * 
 * @author Kevin
 *
 */

public class ProxyLesson extends NormalLesson {

	// Vars
	
	// Constructors
	public ProxyLesson (TimePair time, String name, String teacher, String room) {
		super (time, name, teacher, room);
	}
	
	// Methods
	
	public String toString () {
		String temp = "";
		
		temp += "Vertretung! ";
		
		if (useShorty) {
			temp += "Fach: " + shorty + " \n";
		} else {
			temp += "Fach: " + name + " \n";
		}

		temp += "Teacher: " + teacher + " \n";
		temp += "Room: " + room + " \n";
		
		return temp;
	}
}
