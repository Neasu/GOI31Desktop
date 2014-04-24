package student;

/**
 * Diese Klasse beschreibt ein Vorkommen (eine Stunde) eines Kurses.
 * 
 * @author Valentin
 *
 */
public class Occurrence {
	public Occurrence(int day, int lesson, String room, String teacher)
	{
		setDay(day);
		setLesson(lesson);
		setRoom(room);
		setTeacher(teacher);
	}
	
	private int day = 0;
	private int lesson = 0;
	private String room = "";
	private String teacher = "";
	
	
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the lesson
	 */
	public int getLesson() {
		return lesson;
	}
	/**
	 * @param lesson the lesson to set
	 */
	public void setLesson(int lesson) {
		this.lesson = lesson;
	}
	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}
	/**
	 * @return the teacher
	 */
	public String getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	
}
