package jsfaber.platformer1.image;
	
import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
	
public class ResourceImage {
	
	private BufferedImage image;
	
	private int x;
	private int y;
	
	
	public ResourceImage(String path, int xLoc, int yLoc) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("error reading image in ResourceImage");
		}
		
		x = xLoc;
		y = yLoc;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, null);
	}
	
}
