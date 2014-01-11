package data;

import java.util.Calendar;

import core.LogLevel;
import file.LogFile;

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
	
	public static String formatTime (Calendar cal, String format) {
		String temp = "";
		
		switch (format) {
		
		case "hh:mm": {
			temp = formatTime(cal);
			break;
		}
		
		case "hh:mm:ss": {
			temp += formatTime(cal);
			
			temp += ":";
			
			if (cal.get(Calendar.SECOND) < 10) {
				temp += "0" + cal.get(Calendar.SECOND);
			} else {
				temp += cal.get(Calendar.SECOND);
			}
			
			break;
		}
		
		case "dd.mm.yy hh:mm:ss": {
			temp = formatDate(cal);
			temp += " ";
			
			temp += formatTime(cal);
			temp += ":";
			
			if (cal.get(Calendar.SECOND) < 10) {
				temp += "0" + cal.get(Calendar.SECOND);
			} else {
				temp += cal.get(Calendar.SECOND);
			}
			break;
		}
		
		default: {
			LogFile.getRef().textout("Time couldn't been formatted due to the format: " + format + " doesn't exist.", LogLevel.WARNING);
			temp = format;
			break;
		}
		
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
	
	// Checkt ob die Zeit in dem Zeitraum liegt, dass das TimePair repräsentiert
	public boolean isInBetween (Calendar cal) {
		
		boolean startTimeIsBefore = false;
		boolean endTimeIsAfter = false;
		
		int startTimeHour = startTime.get(Calendar.HOUR_OF_DAY);
		int startTimeMinu = startTime.get(Calendar.MINUTE);
		
		int endTimeHour = endTime.get(Calendar.HOUR_OF_DAY);
		int endTimeMinu = endTime.get(Calendar.MINUTE);
		
		int calHour = cal.get(Calendar.HOUR_OF_DAY);
		int calMinu = cal.get(Calendar.MINUTE);
		
		if (startTimeHour < calHour) {
			
			startTimeIsBefore = true;
			
		} else if (startTimeHour == calHour) {
			
			if (startTimeMinu <= calMinu) {
				startTimeIsBefore = true;
			}
		}
		
		if (endTimeHour > calHour) {
			
			endTimeIsAfter = true;
			
		} else if (endTimeHour == calHour) {
			
			if (endTimeMinu >= calMinu) {
				endTimeIsAfter = true;
			}
		}
		
		if (startTimeIsBefore && endTimeIsAfter) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isInBetween (int hour, int minute) {
		return isInBetween(buildCalendar(hour, minute));
	}
	
	// Gibt den aktuelle Wochentag als Int zurück | 0 wenn ERROR
	public static int getTodayAsInt (Calendar cal) {
		switch (cal.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY: {
			return 1;
		}
		case Calendar.TUESDAY: {
			return 2;
		}
		case Calendar.WEDNESDAY: {
			return 3;
		}
		case Calendar.THURSDAY: {
			return 4;
		}
		case Calendar.FRIDAY: {
			return 5;
		}
		case Calendar.SATURDAY: {
			return 6;
		}
		case Calendar.SUNDAY: {
			return 7;
		}
		default: {
			LogFile.getRef().textout("The current day of the week couldn't been found.", LogLevel.WARNING);
			return 0;
		}
		}
	}
	
	public static String getTodayAsString (Calendar cal) {
		switch (getTodayAsInt(cal)) {
		case 1: {
			return "Montag";
		}
		case 2: {
			return "Dienstag";
		}
		case 3: {
			return "Mittwoch";
		}
		case 4: {
			return "Donnerstag";
		}
		case 5: {
			return "Freitag";
		}
		case 6: {
			return "Samstag";
		}
		case 7: {
			return "Sonntag";
		}
		default: {
			LogFile.getRef().textout("The current day of the week couldn't been found.", LogLevel.WARNING);
			return "Error";
		}
		}
	}
	
	// Gibt die Differez zweier Tageszeiten als String zurück
	public static String getTimeDifferenceAsString (Calendar time1, Calendar time2) {
		String temp = "";
		
		int time1Hour = time1.get(Calendar.HOUR_OF_DAY);
		int time1Minu = time1.get(Calendar.MINUTE);
		int time1Seco = time1.get(Calendar.SECOND);
		
		int time2Hour = time2.get(Calendar.HOUR_OF_DAY);
		int time2Minu = time2.get(Calendar.MINUTE);
		int time2Seco = time2.get(Calendar.SECOND);
		
		int hourDifference = 0;
		int minuDifference = 0;
		int secoDifference = 0;
		
		if ((time1Hour - time2Hour) == 0) {
			hourDifference = 0;
		} else if ((time1Hour - time2Hour) < 0) {
			hourDifference = time2Hour - time1Hour;
		} else if ((time1Hour - time2Hour) > 0) {
			hourDifference = time1Hour - time2Hour;
		}
		
		if ((time1Minu - time2Minu) == 0) {
			minuDifference = 0;
		} else if ((time1Minu - time2Minu) < 0) {
			minuDifference = time2Minu - time1Minu;
		} else if ((time1Minu - time2Minu) > 0) {
			minuDifference = time1Minu - time2Minu;
		}
		
		if ((time1Seco - time2Seco) == 0) {
			secoDifference = 0;
		} else if ((time1Seco - time2Seco) < 0) {
			secoDifference = time2Seco - time1Seco;
		} else if ((time1Seco - time2Seco) > 0) {
			secoDifference = time1Seco - time2Seco;
		}
		
		if (hourDifference < 10) {
			temp += "0" + hourDifference;
		} else {
			temp += hourDifference;
		}
		
		temp += ":";
		
		if (minuDifference < 10) {
			temp += "0" + minuDifference;
		} else {
			temp += minuDifference;
		}
		
		temp += ":";
		
		if (secoDifference < 10) {
			temp += "0" + secoDifference;
		} else {
			temp += secoDifference;
		}
		
		return temp;
	}
	
	// [0] -> Hours | [1] -> Minutes | [2] -> Seconds
	public static int[] getTimeDifferenceAsInt (Calendar time1, Calendar time2) {
		int[] temp = new int [3]; 
		String[] tempString = getTimeDifferenceAsString(time1, time2).split(":");

		for (int i = 0; i < tempString.length && i < temp.length; i++) {
			temp [i] = Integer.getInteger(tempString[i]);
		}
		
		return temp;
	}
}
