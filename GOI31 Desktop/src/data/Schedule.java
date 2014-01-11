package data;

import java.util.Calendar;

import core.LogLevel;
import file.LogFile;

public class Schedule implements core.Updateable {

	// Vars
	private Lesson lessons[][] = new Lesson[5][10]; // Tabelle mit allen Fächern
													// [5] -> 5 Wochentage 
													// [10] -> 10 Stunden am Tag
	private Calendar cal;
	private TimePair times[] = new TimePair[10]; // Tabelle mit den
													// Stundenzeiten

	// Constructors

	// Initialisierung mit Standard-Werten
	public Schedule() {
		Init();
	}

	public Schedule(TimePair[] times) {
		Init ();
		this.times = times;
	}

	// Methods

	// Mit Standardwerten initialisieren
	private void Init() {	
		
		core.Program.addUpdateable(this);
		
		// Standardwerte
		times[0] = new TimePair(8, 0, 8, 45); // 1.Stunde 8:00 - 8:45
		times[1] = new TimePair(8, 45, 9, 30);
		times[2] = new TimePair(9, 45, 10, 30);
		times[3] = new TimePair(10, 30, 11, 15);
		times[4] = new TimePair(11, 30, 12, 15);
		times[5] = new TimePair(12, 15, 13, 0);
		times[6] = new TimePair(13, 30, 14, 15);
		times[7] = new TimePair(14, 15, 15, 0);
		times[8] = new TimePair(15, 15, 16, 0);
		times[9] = new TimePair(16, 0, 16, 45);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				addFreeLesson(i, j, false);
			}
		}
		
		
		
		LogFile.getRef().textout("Schedule has been initialized.", LogLevel.LOG);
	}

	private void addLesson(int day, int hour, Lesson lesson) {

//		if (checkInRange(day, hour)) {
			lessons[day][hour] = lesson;
//		}
	}

	public void addNormalLesson(int day, int hour, String name, String teacher, String room) {
		if (checkInRange(day, hour)) {
			addLesson(day, hour, new NormalLesson(times[hour], name, teacher, room));
			LogFile.getRef().textout("A NormalLesson named " + name + " has been added at Day: " +  day + " and Hour: " + hour + " with Teacher: " + teacher + " and Room: " + room, LogLevel.INFO);
		} else {
			LogFile.getRef().textout("The NormalLesson: " + name + " couldn't been added at Day: " +  day + " and Hour: " + hour + "Day must be between 0 and 4 | Hour must be between 0 and 9!", LogLevel.WARNING);
		}
	}

	public void addFreeLesson(int day, int hour, boolean entfall) {
		if (checkInRange(day, hour)) {
			addLesson(day, hour, new FreeLesson(times[hour], entfall));
			LogFile.getRef().textout("A FreeLesson: has been added at Day: " +  day + " and Hour: " + hour + ". Not applicable: " + entfall, LogLevel.INFO);
		} else {
			LogFile.getRef().textout("The FreeLesson couldn't been added at Day: " +  day + " and Hour: " + hour + "Day must be between 0 and 4 | Hour must be between 0 and 9!", LogLevel.WARNING);
		}
	}

	public void addProxyLesson(int day, int hour, String name, String teacher, String room) {
		if (checkInRange(day, hour)) {
			addLesson(day, hour, new ProxyLesson(times[hour], name, teacher, room));
			LogFile.getRef().textout("A ProxyLesson named " + name + " has been added at Day: " +  day + " and Hour: " + hour + " with Teacher: " + teacher + " and Room: " + room, LogLevel.INFO);
		} else {
			LogFile.getRef().textout("The ProxyLesson: " + name + " couldn't been added at Day: " +  day + " and Hour: " + hour + "Day must be between 0 and 4 | Hour must be between 0 and 9!", LogLevel.WARNING);
		}
	}

	public Lesson getLesson(int day, int hour) {
		if (!checkInRange(day, hour)) {
			return null;
		} else {
			return lessons[day][hour];
		}
	}

	private boolean checkInRange(int day, int hour) {
		if (day < 0 || day > 4 || hour < 0 || hour > 9) {
			return false;
		} else
			return true;
	}

	public TimePair getTimePair(int index) {
		if (index > 9 || index < 0)
			return null;
		return times[index];
	}
	
	public String getCurrTime () {
		String temp = "";
		
		temp = TimePair.formatTime(cal, "dd.mm.yy hh:mm:ss");
		
		return temp;
	}
	
	public void update () {
		cal = Calendar.getInstance();
	}
}
