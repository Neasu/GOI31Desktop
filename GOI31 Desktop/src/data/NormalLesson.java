package data;

import core.LogLevel;
import file.LogFile;

public class NormalLesson extends Lesson {

	// Vars
	protected String name = "";
	protected String teacher = "";
	protected String room = "";
	protected String shorty = ""; // Abkürzung des Faches
	protected boolean useShorty = false; // Abkürzung anstatt des Namens verwenden?

	// Constructors
	public NormalLesson(TimePair time, String name, String teacher, String room) {
		super(time);
		setName(name);
		this.teacher = teacher;
		this.room = room;
	}

	// Methods
	
	// Generiert eine Abkürzung aus dem Namen des Faches
	private String getShortyFromName() {
		String temp = "";

		String[] temp2 = name.split(" ");

		if (temp2.length == 1) {
			temp = name.substring(0, 2);
		} else {
			for (int i = 0; i < temp2.length; i++) {
				temp += temp2[i].substring(0, 1);

				if (i == 2)
					return temp;
			}
		}

		LogFile.getRef().textout("Shorty: " + temp + " has been created from Name: " + name, LogLevel.INFO);
		
		return temp;
	}

	private void setName(String name) {
		if (name.length() > 8) {
			useShorty = true;
		}
		this.name = name;
		shorty = getShortyFromName();
	}

	public String getName() {
		return name;
	}

//	public void setTeacher(String teacher) {
//		this.teacher = teacher;
//	}

	public String getTeacher() {
		return teacher;
	}

//	public void setRoom(String room) {
//		this.room = room;
//	}

	public String getRoom() {
		return room;
	}

	public String getShorty() {
		return shorty;
	}
	
	public String toString() {

		String temp = "";

//		temp += time.getStartTimeAsString() + "/" + time.getEndTimeAsString()
//				+ " \n";

		if (useShorty) {
			temp += "Fach: " + shorty + " \n";
		} else {
			temp += "Fach: " + name + " \n";
		}

		temp += "Lehrer: " + teacher + " \n";
		temp += "Raum: " + room + " \n";

		return temp;

	}
}
