package file;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import file.LogFile;

import javax.imageio.ImageIO;

import core.FunctionResult;

/**
 * 
 * @author Kevin
 *
 */

public class ImageFile extends FileSystem {
	
	// Vars
	
	// Constructor
	
	public ImageFile () { 
	}
	
	public ImageFile (String path) {
		super (path);
	}
	
	public Image getImage () {
		try {
			return ImageIO.read (file);
		} catch (IOException e) {
			LogFile.getRef().functionResult("getImage", FunctionResult.FAIL, e.getMessage());
			return renderPlaceholder();
		}
	}
	
	
	// rederPlaceholder
	// 
	// Rendert eine 100x100px Grafik mit Schachbrettmuster als Platzhalter
	// 
	private Image renderPlaceholder () {
		
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g2d = image.createGraphics();
		
		boolean black = true;
		
		for (int i = 0; i <= 100; i += 10) {
			for (int j = 0; j <= 100; j += 10) {
				
				if (j % 2 == 0)
					black = true;
				
				if (i % 2 == 0) 
					black = !black;
				
				if (black) {
					g2d.setColor(Color.BLACK);
				} else {
					g2d.setColor(Color.PINK);
				}
				
				g2d.drawRect(i, j, 10, 10);
			}
		}
		
		return image;
	}

}
