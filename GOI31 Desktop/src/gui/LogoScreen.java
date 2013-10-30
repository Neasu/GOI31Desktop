package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;

import javax.swing.*;

import file.*;

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
		
		JLabel versionLabel;
		try {
			versionLabel = new JLabel (FileSystem.getReader("/res/version.txt", true).readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			versionLabel = new JLabel("ERORR");
		}
		
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
		Image img = FileSystem.getImage("/res/logo.png", true);
		
		g.drawImage(img,0,0,this);
	}
	
}