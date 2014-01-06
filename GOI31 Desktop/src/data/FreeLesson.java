package data;

public class FreeLesson extends Lesson {

	// Vars
	private boolean entfall = false;
	
	// Constructors
	public FreeLesson (TimePair time) {
		super (time);
	}
	
	// Methods
	public String toString () {
		String temp = "";
		
		if (entfall) {
			temp += ".:Entfall:.";
		} else {
			temp += ".:Frei:.";
		}
		
		return temp;
	}
}
