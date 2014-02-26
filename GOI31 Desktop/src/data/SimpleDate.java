package data;

public class SimpleDate {
	public SimpleDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	
	// Time Variables
	// Default: Beginning of Unix time
	private int day = 1;
	private int month = 1;
	private int year = 1970;
	
	
	// Getters + Setters
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
