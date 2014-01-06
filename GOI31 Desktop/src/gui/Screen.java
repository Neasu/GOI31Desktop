package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class Screen {

	protected int width;
	protected int height;
	protected String screenName;
	protected int screenID;
	protected JFrame frame;

	public void Init() {

	}

	public void setVisible(boolean v) {
		frame.setVisible(v);
	}

	public void centerWindowOnScreen() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int a = (int) dim.getWidth() / 2 - width / 2;
		int b = (int) dim.getHeight() / 2 - height / 2;

		frame.setLocation(a, b);
	}

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
