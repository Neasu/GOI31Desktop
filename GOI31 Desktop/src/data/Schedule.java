package data;

import java.util.Calendar;
import java.util.GregorianCalendar;

import core.LogLevel;
import file.LogFile;

public class Schedule implements core.Updateable {

	// Vars
	private int lessonsPerDay = 10;
	private Lesson lessons[][] = new Lesson[5][lessonsPerDay]; // Tabelle mit allen F�chern [5] -> 5 Wochentage [10] -> 10 Stunden am Tag
	private TimePair schoolDay;
	private Calendar cal = Calendar.getInstance();
	private TimePair times[] = new TimePair[lessonsPerDay]; // Tabelle mit den
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
		
		schoolDay = new TimePair(times[0].getStartTime(), times[lessonsPerDay - 1].getEndTime());
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < lessonsPerDay; j++) {
				addFreeLesson(i, j, false);
			}
		}
		
		addNormalLesson(4, 0, "Deutsch", "KOL", "C011");
		addNormalLesson(4, 1, "Deutsch", "KOL", "C011");
		
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
		if (day < 0 || day > 4 || hour < 0 || hour > lessonsPerDay) {
			return false;
		} else
			return true;
	}

	public TimePair getTimePair(int index) {
		if (index > (lessonsPerDay - 1) || index < 0)
			return null;
		return times[index];
	}
	
	public String getCurrTime () {
		String temp = "";
		
		temp = TimePair.formatTime(cal, "dd.mm.yy hh:mm:ss");
		
		return temp;
	}
	
	public Lesson getCurrentLesson () {
		int today = TimePair.getTodayAsInt(cal);
		
		// Wenn es Wochenende oder au�erhalb der Schulzeiten ist, dann FreeLeson
		if (today == 6 || today == 7 || !schoolDay.isInBetween(cal))
			return new FreeLesson(new TimePair (), false);
		
		
		for (int i = 0; i < lessonsPerDay; i++) {
			if (getLesson(today - 1, i).getTime().isInBetween(cal)) {
				return getLesson (today - 1, i);
			}
		}
		
		LogFile.getRef().textout("The current Lesson couldn't been found.", LogLevel.ERROR);
		return new NormalLesson(new TimePair(), "ERROR", "", "");
	}
	
	public Lesson getNextLesson () {
		int today = TimePair.getTodayAsInt(cal);
		
		Lesson currLesson = getCurrentLesson();
		
		// Wenn es Wochenende oder au�erhalb der Schulzeiten ist, dann FreeLeson
			if (today == 6 || today == 7 || !schoolDay.isInBetween(cal))
				return new FreeLesson(new TimePair (), false);
		
		for (int i = 0; i < lessonsPerDay; i++) {
			
			if (times[i].getEndTimeAsString().equals(currLesson.getTime().getEndTimeAsString())) {
				for (int j = 0; j < lessonsPerDay; j++) {
					if (lessons[today - 1][j].getTime().getStartTimeAsString().equals(times[i + 1].getStartTimeAsString())) {
						return lessons[today - 1][j];
					}
				}
			}
		}
		
		LogFile.getRef().textout("The next Lesson couldn't been found.", LogLevel.ERROR);
		return new NormalLesson(new TimePair (), "ERROR", "", "");
	}
	
	// Gibt die Zeit bis zum Ende der aktuellen Stunde als String zur�ck
	public String getTimeTilLessonEnd () {
		
		Lesson currLesson = getCurrentLesson();
		String temp = "";
		
		if (currLesson.getClass().equals(NormalLesson.class) || currLesson.getClass().equals(ProxyLesson.class))
			return TimePair.getTimeDifferenceAsString(cal, currLesson.getTime().getEndTime());
		
		return temp;
	}
	
	public void update () {
		cal = Calendar.getInstance();
		
		// Standardwerte
//		cal.set(2014, 0, 10, 8, 30);
	}
	
	public void updateData () {
		//TODO Make Code here!
		LogFile.getRef().textout("Updating Schedule Data", LogLevel.LOG);
	}

	public int getLessonsPerDay() {
		return lessonsPerDay;
	}

	public TimePair getSchoolDay() {
		return new TimePair(schoolDay.getStartTime(), schoolDay.getEndTime());
	}

	public Calendar getCal() {
		return (Calendar) cal.clone();
	}
}
