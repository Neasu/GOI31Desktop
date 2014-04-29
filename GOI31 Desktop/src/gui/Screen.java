package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import core.LogLevel;
import file.LogFile;

/**
 * 
 * @author Kevin
 *
 */

public abstract class Screen {

	// Vars
	protected int width;
	protected int height;
	protected String screenName;
	protected int screenID;
	protected JFrame frame;
	protected GUIManager guim;

	
	// Constructors
	public Screen(GUIManager guim) {
		this.guim = guim;
	}
	
	// Methods
	public void Init(GUIManager guim) {
		this.guim = guim;
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
		
		LogFile.getRef().textout("The Screen: " + screenName + " has been centered at X: " + a + " Y: " + b, LogLevel.LOG);
	}
	
	public void resizeToScreen (int xpercent, int ypercent) {
		
		if (xpercent > 100 || xpercent < 0 || ypercent > 100 || ypercent < 0) {
			LogFile.getRef().textout("The Screen: " + screenName + " couldn't been resized to the wished parameters x%: " + xpercent + " y%: " + ypercent, LogLevel.WARNING);
			resizeToScreen();
			return;
		}
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int a = (int) dim.getWidth() * xpercent / 100;
		int b = (int) dim.getHeight() * ypercent / 100;
		
		width = a;
		height = b;
		
		frame.setBounds(0, 0, a, b);
		
		LogFile.getRef().textout("The Screen: " + screenName + " has been resized to Width: " + a + " and Height: " + b , LogLevel.LOG);
		
		centerWindowOnScreen();
	}
	
	public void resizeToScreen () {
		resizeToScreen(70, 50);
	}

	
	// Getters & Setters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getActualWidth () {
		return frame.getBounds().width;
	}
	
	public int getActualHeight () {
		return frame.getBounds().height;
	}

	public String getScreenName() {
		return screenName;
	}

	public int getScreenID() {
		return screenID;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
