package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

import javax.swing.*;

public class LogoScreen extends Screen {

	public void Init ()
	{
		frame = new JFrame("GOI31!");
		
		width = 879;
		height = 600;
		screenName = "Logoscreen";
		screenID = 1;
		
		PaintPanel panel = new PaintPanel();
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setSize(width,height);
		
		frame.setVisible (true);
	}
}

class PaintPanel extends JPanel {
	
	public void paintComponent (Graphics g)
	{
		Image image = new ImageIcon (LogoScreen.class.getResource("/logo.png")).getImage ();
		
		g.drawImage(image,0,0,this);
	}
	
}