package data;

/**
 * 
 * @author Kevin
 *
 */

public abstract class Lesson {

	// Vars
	protected TimePair time;
	

	// Constructors
	public Lesson(TimePair time) {
		this.time = time;
	}
	
	// Getters & Setters
	public TimePair getTime() {
		return time;
	}
	
	public void setTime(TimePair time) {
		this.time = time;
	}

}
