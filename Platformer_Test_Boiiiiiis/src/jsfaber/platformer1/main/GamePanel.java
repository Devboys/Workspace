package jsfaber.platformer1.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import jsfaber.platformer1.gameState.GameStateManager;
import jsfaber.platformer1.handlers.KeyHandler;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	//dimensions
	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;
	
	//game thread
	Thread gameThread;
	boolean running;
	private int fps = 60;
	private long targetTime = 1000 / fps; //convert to millis
	
	//graphics objects
	private BufferedImage bufferImage; //the image buffer
	private Graphics2D g; //graphics2D object to hold image object of bufferImage	
	
	//game state manager
	private GameStateManager gsm;
	
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(gameThread == null) {
			gameThread = new Thread(this);
			addKeyListener(this);
			gameThread.start();
		}
	}
	
	private void init() {	
		bufferImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) bufferImage.getGraphics(); //drawing to this will directly draw to bufferImage
		
		running = true;
		
		gsm = new GameStateManager();
	}
	
	public void run() {
		init();
		
		long elapsed;
		long start;
		long wait;
		
		while(running) {
			
			start = System.currentTimeMillis();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.currentTimeMillis() - start;
			wait = targetTime - elapsed;
			if(wait < 0) wait = 5;
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		gsm.update();
		KeyHandler.update();
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	public void drawToScreen() {
			Graphics g2 = getGraphics();
			g2.drawImage(bufferImage, 0, 0, WIDTH, HEIGHT, null);
			g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		KeyHandler.keySet(e.getKeyCode(), true);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		KeyHandler.keySet(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
}
