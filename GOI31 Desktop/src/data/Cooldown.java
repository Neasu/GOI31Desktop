package data;

import java.util.Calendar;

public class Cooldown implements core.Updateable {
	
	// Vars
	private Calendar cal = Calendar.getInstance();
	private Calendar cooldown = Calendar.getInstance();
	private boolean active = false;
	private int coolDownValue = 0;
	
	// Constructors
	public Cooldown () {
		init (10,  true);
	}
	
	public Cooldown (int cooldown) {
		init (cooldown,  true);
	}
	
	public Cooldown (int cooldown, boolean active) {
		init (cooldown,  active);
	}
	
	// Methods	
	public void init (int cd, boolean active) {
		cal = Calendar.getInstance();
		cooldown = (Calendar) cal.clone();
		
		coolDownValue = cd;
		
		cooldown.add (Calendar.MINUTE, coolDownValue);
		core.Program.addUpdateable(this);
		this.active = active;
	}
	
	public String getTimeLeft () {
		int[] temp = TimePair.getTimeDifferenceAsInt(cooldown, cal);
		if (active) {
			if (coolDownValue < 100) {
				return TimePair.formatTime(temp, "mm:ss");
			} else {
				return TimePair.getTimeDifferenceAsString(cooldown, cal);				
			}
		} else {
			return "00:00:00";
		}
	}
	
	public void reset () {
		cooldown = (Calendar) cal.clone();
		cooldown.add (Calendar.MINUTE, coolDownValue);
		active = false;
	}
	
	public void activate () {
		active = true;
	}
	
	public void restart () {
		reset ();
		activate ();
	}
	
	public void update () {
		cal = Calendar.getInstance();
		
		if (!active) {
			cooldown = (Calendar) cal.clone();
		} else if (cooldown.before(cal)) {
			active = false;
		}
	}

	public Calendar getCooldown() {
		return (Calendar) cooldown.clone();
	}

	public boolean isActive() {
		return active;
	}
}
