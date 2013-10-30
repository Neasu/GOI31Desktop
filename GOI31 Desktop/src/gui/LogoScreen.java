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
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		width = 800;
		height = 605;
		screenName = "Logoscreen";
		screenID = 1;
		
		PaintPanel panel = new PaintPanel();
		
		frame.add(panel, BorderLayout.CENTER);
		
		JLabel versionLabel = new JLabel ("Version: 0.1");
		
		frame.add(versionLabel, BorderLayout.SOUTH);
		
		frame.setSize(width,height);
		
		centerWindowOnScreen();
		
		frame.setVisible (true);
	}
}

class PaintPanel extends JPanel {
	
	public void paintComponent (Graphics g)
	{
		// Ressource aus der JAR holen
		Image image = new ImageIcon (LogoScreen.class.getResource("/logo.png")).getImage ();
		
		g.drawImage(image,0,0,this);
	}
	
}