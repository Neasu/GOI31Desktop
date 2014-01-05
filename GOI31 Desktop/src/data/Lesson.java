package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import core.Core;

public class Lesson {
	
	// Vars
	private TimePair time;
	private String name 				= "";
	private String teacher 				= "";
	private String room 				= "";
	private String shorty				= "";							// Abkürzung des Faches
	private boolean useShorty			= false;						// Abkürzung anstatt des Namens verwenden?
	
	// Constructors
	public Lesson (TimePair time, String name, String teacher, String room) {
		this.time = time;
		setName(name);
		this.teacher = teacher;
		this.room = room;
	}
//	
//	public Lesson (Calendar startTime, Calendar endTime, String name, String teacher, String room) {
//		this.startTime = startTime;
//		this.endTime = endTime;
//		this.name = name;
//		this.teacher = teacher;
//		this.room = room;
//		this.shorty = getShortyFromName();
//	}
//	
//	public Lesson (Calendar startTime, Calendar endTime, String name, String room) {
//		this.startTime = startTime;
//		this.endTime = endTime;
//		this.name = name;
//		this.room = room;
//		this.shorty = getShortyFromName();
//	}
//	
//	public Lesson (Calendar startTime, Calendar endTime, String name) {
//		this.startTime = startTime;
//		this.endTime = endTime;
//		this.name = name;
//		this.shorty = getShortyFromName();
//	}
	
	// Methods
	
	// Generiert eine Abkürzung aus dem Namen des Faches
	private String getShortyFromName () {
		String temp = "";
		
		String[] temp2 = name.split (" ");
		
		if (temp2.length == 1) {
			temp = name.substring(0, 2);
		} else {
			for (int i = 0; i < temp2.length; i++) {
				temp += temp2[i].substring(0,1);
				
				if (i == 2)
					return temp;
			}
		}
		
		//TODO Create Class that handels this
		if (Core.DEBUG) {
			System.out.println("Shorty:" + temp + " created from: " + name);
		}
		return temp;
	}

	public void setName (String name) {
		if (name.length() > 12) {
			useShorty = true;
		}
		this.name = name;
		getShortyFromName();
	}
	
	public String getName () {
		return name;
	}
	
	public void setTeacher (String teacher) {
		this.teacher = teacher;
	}

	public String getTeacher () {
		return teacher;
	}
	
	public void setRoom (String room) {
		this.room = room;
	}
	
	public String getRoom () {
		return room;
	}
	
	public String getShorty () {
		return shorty;
	}
	
	public String toString () {
		
		String temp = "";
		
		temp += time.getStartTimeAsString() + "/" + time.getEndTimeAsString() + " \n";
		
		if (useShorty) {
			temp += "Name: " + shorty + " \n";
		} else {
			temp += "Name: " + name + " \n";
			temp += "Shorty: " + shorty + " \n";
		}
		
		temp += "Teacher: " + teacher + " \n";
		temp += "Room: " + room + " \n";
		
		return temp;
		
	}
	
}
