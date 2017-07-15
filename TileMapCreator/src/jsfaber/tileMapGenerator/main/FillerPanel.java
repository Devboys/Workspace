package jsfaber.tileMapGenerator.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FillerPanel extends JPanel {
	private int WIDTH = 20;
	private int HEIGHT = 20;
	
	private Graphics2D g;
	private BufferedImage bufferImage;
	private Font theFont;
	
	private int currTileSelection;
	
	public FillerPanel() {
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(false);
		
	}
	
	public void init() {
		bufferImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) bufferImage.getGraphics();
		
		theFont = new Font("Arial", Font.BOLD, 12);
		currTileSelection = 10;
	}
	
	public void update() {
		currTileSelection = MapGenerator.getCurrentMode();
	}
	
	public void draw() {
		g.setColor(new Color(0, 0, 0));
		g.setFont(theFont);
		g.drawRect(0, 0, WIDTH, HEIGHT);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(255, 255, 255));
		g.drawString(Integer.toString(currTileSelection), 3, 15);
	}
	
	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(bufferImage, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
