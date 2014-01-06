package data;

public class FreeLesson extends Lesson {

	// Vars
	private boolean entfall = false;
	
	// Constructors
	public FreeLesson (TimePair time, boolean entfall) {
		super (time);
		this.entfall = entfall;
	}
	
	// Methods
	public String toString () {
		String temp = "";
		
		if (entfall)
			temp += ".:Entfall:.";
		
		return temp;
	}
}
