package student;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	public Profile(String _user, String _pass) {
		this.user = _user;
		this.pass = _pass;
	}
	
	private String user = null;
	private String pass = null;
	private String firstname = null;
	private String lastname = null;
	
	private List<Course> courses = new ArrayList<Course>();
}
