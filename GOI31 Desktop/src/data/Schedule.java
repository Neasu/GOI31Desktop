package data;

import java.util.Calendar;

public class Schedule {
	
	//Vars
	private Lesson lessons[][] = new Lesson[5][10];;
	private TimePair times[] = new TimePair [10];
	
	//Methods
	public void Init () {
		
		// Standardwerte
		times[0] = new TimePair(8, 0, 8, 45); // 1.Stunde 8:00 - 8:45
		times[1] = new TimePair(8, 45, 9,30);
		times[2] = new TimePair(9, 45, 10, 30);
		times[3] = new TimePair(10, 30, 11, 15);
		times[4] = new TimePair(11, 30, 12, 15);
		times[5] = new TimePair(12, 15, 13, 0);
		times[6] = new TimePair(13, 30, 14, 15);
		times[7] = new TimePair(14, 15, 15, 0);
		times[8] = new TimePair(15, 15, 16, 0);
		times[9] = new TimePair(16, 0, 16, 45);
		
	}
	
	public TimePair getTimePair (int index) {
		if (index > 9 || index < 0)
			return null;
		return times[index];
	}
}
