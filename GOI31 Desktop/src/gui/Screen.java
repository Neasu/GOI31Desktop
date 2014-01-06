package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class Screen {

	// Vars
	protected int width;
	protected int height;
	protected String screenName;
	protected int screenID;
	protected JFrame frame;

	
	// Methods
	public void Init() {

	}

	public void setVisible(boolean v) {
		frame.setVisible(v);
	}

	
	// Zentriert das Fenster auf dem Bildschirm
	public void centerWindowOnScreen() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int a = (int) dim.getWidth() / 2 - width / 2;
		int b = (int) dim.getHeight() / 2 - height / 2;

		frame.setLocation(a, b);
	}

	
	// Getters & Setters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getScreenName() {
		return screenName;
	}

	public int getScreenID() {
		return screenID;
	}
}
