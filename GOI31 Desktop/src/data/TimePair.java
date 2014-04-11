package data;

import java.util.Calendar;

import core.LogLevel;
import file.LogFile;

/**
 * 
 * @author Kevin
 *
 */

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
		startTime = buildCalendar(startHour, startMinute, 0);
		endTime = buildCalendar(endHour, endMinute, 0);
	}
	
	public TimePair(int startHour, int startMinute, int startSecond, int endHour, int endMinute, int endSecond) {
		startTime = buildCalendar(startHour, startMinute, startSecond);
		endTime = buildCalendar(endHour, endMinute, endSecond);
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
	
	public void parseString (String time) {
		
		try {
			String[] temp;
			String[] temp2;
			
			temp = time.split("/");
			
			temp[0].replace(" ", ".");
			temp[1].replace(" ", ".");
			
			temp2 = temp[0].split("\\.");
			
			startTime = buildCalendar(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]), Integer.parseInt(temp2[3]), Integer.parseInt(temp2[4]), Integer.parseInt(temp2[5]));
			
			temp2 = temp[1].split("\\.");
			
			endTime = buildCalendar(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]), Integer.parseInt(temp2[3]), Integer.parseInt(temp2[4]), Integer.parseInt(temp2[5]));
		} catch (Exception e) {
			LogFile.getRef().textout("TimePair couldn't been parsed due to " + e.getMessage(), LogLevel.WARNING);
		}
		
	}

	public String toString() {
		String temp = "";
		
		temp += formatTime(startTime, "dd.mm.yy hh.mm.ss");
		
		temp += "/";
		
		temp += formatTime(endTime, "dd.mm.yy hh.mm.ss");
		
		return temp;
	}

	// Kleine Toolmethode um einen passenden Kalender für die Klasse zu bauen
	public static Calendar buildCalendar(int hours, int minutes) {

		if (hours > 24) {
			hours = 00;
		}
		
		if (minutes > 59) {
			minutes = 00;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);

		return cal;
	}
	
	public static Calendar buildCalendar(int hours, int minutes, int seconds) {
		
		if (seconds > 59) {
			seconds = 00;
		}

		Calendar cal = buildCalendar(hours, minutes);
		cal.set(Calendar.SECOND, seconds);

		return cal;
	}
	
public static Calendar buildCalendar(int days, int months, int years, int hours, int minutes, int seconds) {

		Calendar cal = buildCalendar(hours, minutes, seconds);
		
		cal.set(Calendar.YEAR, years);
		cal.set(Calendar.MONTH, months - 1);
		cal.set(Calendar.DAY_OF_MONTH, days);
		
		return cal;
	}
	
	
	// Gibt die Zeit als hh:mm aus
	public static String formatTime (Calendar cal) {
		String temp = "";
		
		temp = formatTime(cal, "hh:mm");
		
		return temp;
	}

	
	public static String formatTime (Calendar cal, String format) {
		String temp = "";
		
		int[] intTemp = {
				cal.get (Calendar.SECOND),
				cal.get (Calendar.MINUTE),
				cal.get (Calendar.HOUR_OF_DAY),
				cal.get (Calendar.DAY_OF_MONTH),
				cal.get (Calendar.MONTH) + 1,
				cal.get (Calendar.YEAR),
		};
		
		temp = formatTime(intTemp, format);
		
		return temp;
	}
	
	
	/**
	 * 
	 * @param time 	[0] Seconds [1] Minutes [2] Hours [4] Days [5] Months [6] Years
	 * @param format Available Formats: "ss", "mm", "hh", "mm:ss", "mm.ss", "hh:mm", "hh.mm", "hh:mm:ss", "hh.mm.ss", "dd.mm.yy", "dd.mm.yy hh:mm:ss", "dd.mm.yy hh.mm.ss"
	 * @return
	 */
	public static String formatTime(int[] time,  String format) {
		String temp = "";
		
		switch (format) {
			case "ss": {
				
				if (time[0] < 10) {
					temp += "0" + time[0];
				} else {
					temp += time[0];
				}
				
				break;
			}
			case "mm": {
				
				if (time[1] < 10) {
					temp += "0" + time[1];
				} else {
					temp += time[1];
				}
				
				break;
			}
			case "hh": {
				
				if (time[2] < 10) {
					temp += "0" + time[2];
				} else {
					temp += time[2];
				}
				
				break;
			}
			case "mm:ss": {
				
				temp += formatTime(time, "mm");
				temp += ":";
				temp += formatTime(time, "ss");
				
				break;
			}
			case "mm.ss": {
				
				temp += formatTime(time, "mm:ss").replace(":", ".");
				
				break;
			}
			case "hh:mm": {
				
				temp += formatTime(time, "hh");
				temp += ":";
				temp += formatTime(time, "mm");
				
				break;
			}
			case "hh.mm": {
				
				temp += formatTime(time, "hh:mm").replace(":", ".");
				
				break;
			}
			case "hh:mm:ss": {
				
				temp += formatTime(time, "hh:mm");
				temp += ":";
				temp += formatTime(time, "ss");
				
				break;
			}
			case "hh.mm.ss": {
				
				temp += formatTime(time, "hh:mm:ss").replace(":", ".");
				
				break;
			}
			case "dd.mm.yy": {
				
				if (time[3] < 10) {
					temp += "0" + time[3];
				} else {
					temp += time[3];
				}
				
				temp += ".";
				
				if (time[4] < 10) {
					temp += "0" + time[4];
				} else {
					temp += time[4];
				}
				
				temp += ".";
				
				if (time[5] < 10) {
					temp += "0" + time[5];
				} else {
					temp += time[5];
				}
				
				break;
			}
			case "dd.mm.yy hh:mm:ss": {
				
				temp += formatTime(time, "dd.mm.yy");
				
				temp += " ";
				
				temp += formatTime(time, "hh:mm:ss");
				
				break;
			}
			case "dd.mm.yy hh.mm.ss": {
			
				temp += formatTime(time, "dd.mm.yy hh:mm:ss").replace(":", ".");
				
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
	
	// Checkt ob die Zeit in dem Zeitraum liegt, dass das TimePair repräsentiert
	public boolean isInBetween (Calendar cal) {
		
		boolean startTimeIsBefore = false;
		boolean endTimeIsAfter = false;
		
		int startTimeHour = startTime.get(Calendar.HOUR_OF_DAY);
		int startTimeMinu = startTime.get(Calendar.MINUTE);
		int startTimeSeco = startTime.get(Calendar.SECOND);
		
		int endTimeHour = endTime.get(Calendar.HOUR_OF_DAY);
		int endTimeMinu = endTime.get(Calendar.MINUTE);
		int endTimeSeco = endTime.get(Calendar.SECOND);
		
		int calHour = cal.get(Calendar.HOUR_OF_DAY);
		int calMinu = cal.get(Calendar.MINUTE);
		int calSeco = cal.get(Calendar.SECOND);
		
		if (startTimeHour < calHour) {
			
			startTimeIsBefore = true;
			
		} else if (startTimeHour == calHour) {
			
			if (startTimeMinu < calMinu) {
				
				startTimeIsBefore = true;
				
			} else if (startTimeMinu == calMinu) {
				
				if (startTimeSeco <= calSeco) {
					
					startTimeIsBefore = true;
					
				}
				
			}
		}
		
		if (endTimeHour > calHour) {
			
			endTimeIsAfter = true;
			
		} else if (endTimeHour == calHour) {
			
			if (endTimeMinu > calMinu) {
				
				endTimeIsAfter = true;
				
			} else if (endTimeMinu == calMinu) {
				
				if (endTimeSeco >= calSeco) {
					endTimeIsAfter = true;
				}
				
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
		
		temp = formatTime(getTimeDifferenceAsInt(time1, time2), "hh:mm:ss");
		
		return temp;
	}
	
	
	
	// [2] -> Hours | [1] -> Minutes | [0] -> Seconds
	public static int[] getTimeDifferenceAsInt (Calendar time1, Calendar time2) {
		int[] temp = new int [3]; 
		
		int milliesDifference = 0;
		
		int hourDifference = 0;
		int minuDifference = 0;
		int secoDifference = 0;
		
		milliesDifference = (int) (time1.getTimeInMillis() - time2.getTimeInMillis());
		
		if (milliesDifference < 0)
			milliesDifference = milliesDifference * (-1);
		
		secoDifference = (int) milliesDifference / 1000;
		minuDifference = (int) secoDifference / 60;
		hourDifference = (int) minuDifference / 60;
		
		temp[0] = secoDifference % 60;
		temp[1] = minuDifference % 60;
		temp[2] = hourDifference % 24;
		
		return temp;
	}
}
