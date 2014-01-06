package data;

import core.FunctionResult;
import file.LogFile;


public class Schedule {

	// Vars
	private Lesson lessons[][] = new Lesson[5][10];
	private TimePair times[] = new TimePair[10];

	// Constructors
	public Schedule () {
		Init ();
	}
	
	// Methods
	private void Init() {

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
				lessons[i][j] = new NormalLesson (times[j], "Placeholder", "PLH", "C000");
			}
		}
		
//		lessons[0][3] = new NormalLesson (times[3], "Mathematik", "KOL", "C014");
		addNormalLesson(0, 3, "Mathematik", "WE", "C005");
//		lessons[0][1] = new FreeLesson   (times[1]);
		addFreeLesson(0, 1);
//		lessons[0][9] = new ProxyLesson  (times[9], "Deutsch", "WOL", "C013");
		addProxyLesson(0, 9, "Wirtschaftslehre", "KOF", "C011");

	}
	
	public void addLesson (int day, int hour, Lesson lesson) {
		
		if (checkInRange(day, hour)) {
			lessons[day][hour] = lesson;
		}
	}
	
	public void addNormalLesson (int day, int hour, String name, String teacher, String room) {
		if (checkInRange(day, hour))
			addLesson (day, hour, new NormalLesson(times[hour], name, teacher, room));
	}
	
	public void addFreeLesson (int day, int hour) {
		if (checkInRange(day, hour))
			addLesson (day, hour, new FreeLesson(times[hour]));
	}
	
	public void addProxyLesson (int day, int hour, String name, String teacher, String room) {
		if (checkInRange(day, hour))
			addLesson (day, hour, new ProxyLesson(times[hour], name, teacher, room));
	}
	
	public Lesson getLesson (int day, int hour) {
		if (!checkInRange(day, hour)) {
			return null;
		} else {
			return lessons[day][hour];
		}
	}
	
	private boolean checkInRange (int day, int hour) {
		if (day < 0 || day > 4 || hour < 0 || hour > 9) {
			LogFile.getRef().functionResult("getLesson", FunctionResult.FAIL, "Day must be between 0 and 4 | Hour must be between 0 and 9!");
			return false;
	} else 
			return true;
	}

	public TimePair getTimePair(int index) {
		if (index > 9 || index < 0)
			return null;
		return times[index];
	}
}
