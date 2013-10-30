package gui;

import javax.swing.*;
import java.awt.*;

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
	
	public void centerWindowOnScreen () {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Google-is-your-friend-code
		int a = (int) dim.getWidth() / 2 - width / 2;
		int b = (int) dim.getHeight() / 2 - height / 2;
		
		frame.setLocation(a, b);
	}
}
