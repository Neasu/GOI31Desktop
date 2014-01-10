package data;

import java.util.Calendar;

public class TimePair {

	// Vars
	private Calendar startTime;
	private Calendar endTime;

	// Constructors
	public TimePair() {
		startTime = buildCalendar(0, 0);
		endTime = buildCalendar(0, 0);
	}

	public TimePair(int startHour, int startMinute, int endHour, int endMinute) {
		startTime = buildCalendar(startHour, startMinute);
		endTime = buildCalendar(endHour, endMinute);
	}

	public TimePair(Calendar start, Calendar end) {
		startTime = start;
		endTime = end;
	}

	// Methods
	public void setStartTime(Calendar start) {
		startTime = start;
	}

	public void setStartTime(int startHour, int startMinute) {
		startTime = buildCalendar(startHour, startMinute);
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public String getStartTimeAsString() {
		return formatTime(startTime);
	}

	public void setEndTime(Calendar end) {
		endTime = end;
	}

	public void setEndTime(int endHour, int endMinute) {
		endTime = buildCalendar(endHour, endMinute);
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public String getEndTimeAsString() {
		return formatTime(endTime);
	}

	public String getTimePairAsString() {
		String temp = "";

		temp += getStartTimeAsString();

		temp += " / ";

		temp += getEndTimeAsString();

		return temp;
	}

	public String toString() {
		return getTimePairAsString();
	}

	// Kleine Toolmethode um einen passenden Kalender für die Klasse zu bauen
	public static Calendar buildCalendar(int hours, int minutes) {

		if (hours > 24) {
			hours = 00;
		} else if (minutes > 59) {
			minutes = 00;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);

		return cal;
	}
	
	// Gibt die Zeit als hh:mm aus
	public static String formatTime (Calendar cal) {
		String temp = "";
		
		if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
			temp += "0" + cal.get(Calendar.HOUR_OF_DAY);
		} else {
			temp += cal.get(Calendar.HOUR_OF_DAY);
		}

		temp += ":";

		if (cal.get(Calendar.MINUTE) < 10) {
			temp += "0" + cal.get(Calendar.MINUTE);
		} else {
			temp += cal.get(Calendar.MINUTE);
		}
		
		return temp;
	}
	
	// Gibt das Datum als dd.mm.yy aus
	public static String formatDate (Calendar cal) {
		String temp = "";
		String tempYear = "";
		
		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			temp += "0" + cal.get(Calendar.DAY_OF_MONTH);
		} else {
			temp += cal.get(Calendar.DAY_OF_MONTH);
		}
		
		temp += ".";
		
		if ((cal.get(Calendar.MONTH) + 1) < 10) {
			temp += "0" + (cal.get(Calendar.MONTH) + 1);
		} else {
			temp += (cal.get(Calendar.MONTH) + 1);
		}
		
		temp += ".";
		
		tempYear = "" + cal.get (Calendar.YEAR);
		tempYear = tempYear.substring(2, 4);
		
		temp += tempYear;
		
		return temp;
	}
}
