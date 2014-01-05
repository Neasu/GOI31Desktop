package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimePair {

	// Vars
	private Calendar startTime;
	private Calendar endTime;
	private SimpleDateFormat df	= new SimpleDateFormat ("hh:mm");
	
	// Constructors
	public TimePair () {
		startTime = buildCalendar(0, 0);
		endTime = buildCalendar(0, 0);
	}
	
	public TimePair (int startHour, int startMinute, int endHour, int endMinute) {
		startTime = buildCalendar(startHour, startMinute);
		endTime = buildCalendar(endHour, endMinute);
	}
	
	public TimePair (Calendar start, Calendar end) {
		startTime = start;
		endTime = end;
	}
	
	// Methods
	public void setStartTime (Calendar start) {
		startTime = start;
	}
	
	public void setStartTime (int startHour, int startMinute) {
		startTime = buildCalendar(startHour, startMinute);
	}
	
	public Calendar getStartTime () {
		return startTime;
	}
	
	public String getStartTimeAsString () {
		return df.format(startTime.getTime ());
	}
	
	public void setEndTime (Calendar end) {
		endTime = end;
	}
	
	public void setEndTime (int endHour, int endMinute) {
		endTime = buildCalendar(endHour, endMinute);
	}
	
	public Calendar getEndTime () {
		return endTime;
	}
	
	public String getEndTimeAsString () {
		return df.format(endTime.getTime ());
	}
	
	public String getTimePairAsString () {
		String temp = "";
		
		temp += getStartTimeAsString();
		
		if (startTime.get(Calendar.AM_PM) == Calendar.AM) {
			temp += "am";
		} else {
			temp += "pm";
		}
		
		temp += " / ";
		
		temp += getEndTimeAsString ();
		
		if (endTime.get(Calendar.AM_PM) == Calendar.AM) {
			temp += "am";
		} else {
			temp += "pm";
		}
		
		return temp;
	}
	
	// Kleine Toolmethode um einen passenden Kalender für die Klasse zu bauen
		public static Calendar buildCalendar (int hours, int minutes) {
			
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
}
