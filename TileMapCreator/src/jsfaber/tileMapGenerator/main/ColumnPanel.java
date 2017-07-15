package jsfaber.tileMapGenerator.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import jsfaber.tileMapGenerator.handlers.ImageHandler;

@SuppressWarnings("serial")
public class ColumnPanel extends JPanel { //for x-index/column tracking
	
	public static final int WIDTH = 960;
	public static final int HEIGHT = 20;
	
	private final int nDisplayedColumns = 32;
	
	private Graphics2D g;
	private BufferedImage bufferImage;
	
	private BufferedImage bg;
	private Font theFont;
	
	public ColumnPanel() {
		super();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(false);
	}
	
	public void init() {
		bufferImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) bufferImage.getGraphics();
		
		bg = ImageHandler.columnBG;
		theFont = new Font("Arial", Font.BOLD, 12);
	}
	
	public void update() {}
	
	public void draw() {
		g.drawImage(bg, 0, 0, null);
		g.setColor(new Color(0, 0, 0));
		g.setFont(theFont);
		for(int i = 0; i < nDisplayedColumns; i++) {
			g.drawLine(i * 30, 0, i * 30, 20);
			g.drawString(Integer.toString(i), (i * 30) + 10, 15);
		}
	}
	
	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(bufferImage, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
