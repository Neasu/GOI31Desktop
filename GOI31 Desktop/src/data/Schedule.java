package data;

import java.util.Calendar;

import core.LogLevel;
import file.LogFile;

/**
 * 
 * @author Kevin
 *
 */

public class Schedule implements core.Updateable {

	// Vars
	private int lessonsPerDay = 10;
	private Lesson lessons[][] = new Lesson[5][lessonsPerDay]; // Tabelle mit allen Fächern [5] -> 5 Wochentage [10] -> 10 Stunden am Tag
	private TimePair schoolDay;
	private Calendar cal = Calendar.getInstance();
	private TimePair times[] = new TimePair[lessonsPerDay]; // Tabelle mit den Stundenzeiten
	private NormalLesson errorLesson = new NormalLesson(new TimePair(), "ERROR", "", "");
	private NormalLesson pauseLesson = new NormalLesson(new TimePair(), "Pause", "", "");

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
			for (int j = 0; j < lessonsPerDay; j++) {
				addFreeLesson(i, j, false);
			}
		}
		
		//Montag
		addNormalLesson(0, 2, "Mathe", "WE", "C005");
		addNormalLesson(0, 3, "Mathe", "WE", "C005");
		
		addNormalLesson(0, 4, "Informatik", "BON", "C115");
		addNormalLesson(0, 5, "Informatik", "BON", "C115");
		
		// Dienstag
		addNormalLesson(1, 0, "Physik", "TWE", "A135");
		addNormalLesson(1, 1, "Physik", "TWE", "A135");
		
		addNormalLesson(1, 4, "Mathe", "WE", "C017");
		addNormalLesson(1, 5, "Mathe", "WE", "C017");
		
		addNormalLesson(1, 6, "Soziologie", "GRA", "C011");
		addNormalLesson(1, 7, "Soziologie", "GRA", "C011");
		
		addNormalLesson(1, 8, "Medieninformatik", "WÖ", "C115");
		addNormalLesson(1, 9, "Medieninformatik", "WÖ", "C115");
		
		// Mittwoch
		addNormalLesson(2, 0, "Spanisch", "PU", "C106");
		addNormalLesson(2, 1, "Spanisch", "PU", "C106");
		
		addNormalLesson(2, 2, "Informatik", "BL", "C113");
		addNormalLesson(2, 3, "Informatik", "BL", "C113");
		
		addNormalLesson(2, 4, "Englisch", "DIN", "C106");
		addNormalLesson(2, 5, "Englisch", "DIN", "C106");
		
		addNormalLesson(2, 6, "Sport", "WE", "SH4");
		addProxyLesson(2, 7, "Sport", "GRA", "C001");
		
		// Donnerstag
		addNormalLesson(3, 2, "Gesellschaftslehre Geschichte", "KOF", "C013");
		addNormalLesson(3, 3, "Gesellschaftslehre Geschichte", "KOF", "C013");
		
		addNormalLesson(3, 4, "Deutsch", "KOL", "C013");
		
		addNormalLesson(3, 5, "Informatik", "BON", "C115");
		
		addNormalLesson(3, 6, "Spanisch", "PU", "C106");
		addNormalLesson(3, 7, "Spanisch", "PU", "C106");
		
		// Freitag
		addFreeLesson(4, 0, true);
		addFreeLesson(4, 1, true);
		
		addNormalLesson(4, 2, "Mathe", "WE", "C013");
		
		addNormalLesson(4, 3, "Englisch", "DIN", "C106");
		
		addNormalLesson(4, 4, "Wirtschaftslehre", "WOL", "C008");
		addNormalLesson(4, 5, "Wirtschaftslehre", "WOL", "C008");
		
		addNormalLesson(4, 6, "Techinsche Informatik", "BO", "C019");
		addNormalLesson(4, 7, "Techinsche Informatik", "BO", "C019");
		
		// Darf erst nach der Initialisierung des Stundenplans aufgerufen werden!
		updateSchoolday();
		
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
		TimePair tempTP;
		
		// Wenn es Wochenende oder außerhalb der Schulzeiten ist, dann FreeLesson
		if (today == 6 || today == 7 || !schoolDay.isInBetween(cal))
			return new FreeLesson(new TimePair (), false);
		
		
		for (int i = 0; i < lessonsPerDay; i++) {
			if (getLesson(today - 1, i).getTime().isInBetween(cal)) {
				return getLesson (today - 1, i);
			} else {
				// Zeit zwischen der Aktuellen und der nächsten Stunde
				tempTP = new TimePair (getLesson (today - 1, i).getTime().getEndTime(), getLesson(today - 1, i + 1).getTime().getStartTime());
				
				if (tempTP.isInBetween(cal)) {
					pauseLesson.setTime(tempTP);
					return pauseLesson;
				}
				
			}
		}
		
		LogFile.getRef().textout("The current Lesson couldn't been found.", LogLevel.ERROR);
		return errorLesson;
	}
	
	public Lesson getNextLesson () {
		int today = TimePair.getTodayAsInt(cal);
		
		Lesson currLesson = getCurrentLesson();
		NormalLesson nl = null;
		
		if (currLesson.getClass().equals(NormalLesson.class)) {
			nl = (NormalLesson) currLesson;
		}
		
		// Wenn es Wochenende oder außerhalb der Schulzeiten ist, dann FreeLeson
			if (today == 6 || today == 7 || !schoolDay.isInBetween(cal))
				return new FreeLesson(new TimePair (), false);
		
		for (int i = 0; i < lessonsPerDay; i++) {
			
			if (nl != null && nl.getName().equals("Pause")) {
				
				if (times[i].getStartTimeAsString().equals(nl.getTime().getEndTimeAsString())) {
					for (int j = 0; j < lessonsPerDay; j++) {
						if (lessons[today - 1][j].getTime().getStartTimeAsString().equals(times[i].getStartTimeAsString())) {
							return lessons[today - 1][j];
						}
					}
				}
				
			} else if (times[i].getEndTimeAsString().equals(currLesson.getTime().getEndTimeAsString())) {
				for (int j = 0; j < lessonsPerDay; j++) {
					if (lessons[today - 1][j].getTime().getStartTimeAsString().equals(times[i + 1].getStartTimeAsString())) {
						return lessons[today - 1][j];
					}
				}
			}
		}
		
		LogFile.getRef().textout("The next Lesson couldn't been found.", LogLevel.ERROR);
		return errorLesson;
	}
	
	// Gibt die Zeit bis zum Ende der aktuellen Stunde als String zurück
	public String getTimeTilLessonEnd () {
		
		Lesson currLesson = getCurrentLesson();
		String temp = "";
		
		if (currLesson.getClass().equals(NormalLesson.class) || currLesson.getClass().equals(ProxyLesson.class))
			return TimePair.getTimeDifferenceAsString(cal, currLesson.getTime().getEndTime());
		
		return temp;
	}
	
	public Lesson getTodaysFirstLesson () {
		int today = TimePair.getTodayAsInt(cal);
		
		if (today == 6 || today == 7) {
			return new FreeLesson(new TimePair (), false);
		}
		
		for (int i = 0; i < lessonsPerDay; i++) {
			if (!IsLessonIgnoreable(lessons[today - 1][i])) {
				LogFile.getRef().textout("Todays first Lesson is: " + ((NormalLesson) lessons[today - 1][i]).getName(), LogLevel.INFO);
				return lessons[today - 1] [i];
			}
		}
		
		LogFile.getRef().textout("Todays first Lesson couldn't been found!", LogLevel.ERROR);
		return errorLesson;
	
	}
	
	public Lesson getTodaysLastLesson () {
		// Wann ist es die letzte Stunde? Wenn alle folgenden Stunden frei sind!
		
		int today = TimePair.getTodayAsInt(cal);
		boolean isLastLesson = false;
		
		if (today == 6 || today == 7) {
			return new FreeLesson(new TimePair (), false);
		}
		
		for (int i = 0; i < lessonsPerDay; i++) {
			
			if (!IsLessonIgnoreable(lessons[today - 1][i])) {
				
				isLastLesson = true;
				
				for (int j = i + 1; j < lessonsPerDay; j++) {
					if (!IsLessonIgnoreable(lessons[today - 1][j])) {
						isLastLesson = false;
						break;
					}
				}
				
				if (isLastLesson) {
					LogFile.getRef().textout("Todays last Lesson is: " + ((NormalLesson) lessons[today - 1][i]).getName(), LogLevel.INFO);
					return lessons[today - 1][i];
				}
				
			}
		}
		
		LogFile.getRef().textout("Todays last Lesson couldn't been found!", LogLevel.ERROR);
		return errorLesson;
	}
	
	public boolean IsLessonIgnoreable (Lesson less) {
		if (less.getClass().equals(NormalLesson.class)) {
			return false;
		} else if (less.getClass().equals(ProxyLesson.class)) {
			return false;
		} else {
			return true;
		}
	}
	
	public void renewTimes () {
		for (int i = 0; i < times.length; i++) {
			times[i] = new TimePair(times[i].getStartTime().get(Calendar.HOUR_OF_DAY), times[i].getStartTime().get(Calendar.MINUTE), times[i].getEndTime().get(Calendar.HOUR_OF_DAY), times[i].getEndTime().get(Calendar.MINUTE));
		}
	}
	
	public void updateSchoolday () {
		schoolDay = new TimePair(getTodaysFirstLesson().getTime().getStartTime(), getTodaysLastLesson().getTime().getEndTime());
	}
	
	public void update () {
		cal = Calendar.getInstance();
		
		// Standardwerte
//		cal.set(2014, 0, 10, 8, 30);
	}
	
	public void updateData () {
		//TODO Make Code here!
		LogFile.getRef().textout("Updating Schedule Data", LogLevel.LOG);
		
		renewTimes();
		
		updateSchoolday();
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
