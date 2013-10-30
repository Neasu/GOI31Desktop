package gui;

import javax.swing.*;

public abstract class Screen {

	int width;
	int height;
	String screenName;
	int screenID;
	JFrame frame;
	
	public void Init ()
	{
		
	}
	
	public void setVisible (boolean v)
	{
		frame.setVisible(v);
	}
}
