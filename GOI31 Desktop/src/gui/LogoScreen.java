package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

import file.*;

public class LogoScreen extends Screen {
	
	private Image img;

	public void Init ()
	{
		// Bild laden
		img = new ImageFile ("logo.png").getImage();
		
		frame = new JFrame(core.Core.NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		width = 800;
		height = 605;
		screenName = "Logoscreen";
		screenID = 1;
		
		PaintPanel panel = new PaintPanel();
		
		frame.add(panel, BorderLayout.CENTER);
		
		JLabel versionLabel = new JLabel("Version: " + core.Core.VERSION);
		
		
		frame.add(versionLabel, BorderLayout.SOUTH);
		
		frame.setSize(width,height);
		
		centerWindowOnScreen();
		
		frame.setVisible (true);
	}


// Innere Klasse um das Bild zu zeichnen
	class PaintPanel extends JPanel {
		
		public void paintComponent (Graphics g)
		{	
			g.drawImage(img,0,0,this);
		}
	
	}
}