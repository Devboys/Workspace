package jsfaber.tileMapGenerator.main;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SuperPanel extends JPanel implements Runnable {
	
	public FillerPanel fillerPanel;
	public ColumnPanel columnPanel;
	public CreatorPanel creatorPanel;
	public RowPanel rowPanel;
	
	private Thread mainThread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS; //conversion to miliseconds
	
	
	public SuperPanel() {
		super();
		this.setFocusable(false);
		this.setPreferredSize(new Dimension(CreatorPanel.WIDTH + RowPanel.WIDTH, ColumnPanel.HEIGHT + CreatorPanel.HEIGHT) );
		this.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
		fillerPanel = new FillerPanel();
		columnPanel = new ColumnPanel();
		creatorPanel = new CreatorPanel();
		rowPanel = new RowPanel();
		this.add(fillerPanel);
		this.add(columnPanel);
		this.add(rowPanel);
		this.add(creatorPanel);
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(mainThread == null) {
			mainThread = new Thread(this);
			mainThread.start();
		}
	}
	
	@Override
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
	
	public void init() {
		fillerPanel.init();
		columnPanel.init();
		creatorPanel.init();
		rowPanel.init();
		
		running = true;
	}
	
	public void update() {
		fillerPanel.update();
		columnPanel.update();
		creatorPanel.update();
		rowPanel.update();
	}
	
	public void draw() {
		columnPanel.draw();
		creatorPanel.draw();
		rowPanel.draw();
		fillerPanel.draw();
	}
	
	public void drawToScreen() {
		columnPanel.drawToScreen();
		creatorPanel.drawToScreen();
		rowPanel.drawToScreen();
		fillerPanel.drawToScreen();
	}
}
