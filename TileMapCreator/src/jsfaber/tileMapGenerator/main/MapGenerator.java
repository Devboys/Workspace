package jsfaber.tileMapGenerator.main;

import java.awt.Graphics2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import jsfaber.tileMapGenerator.handlers.ImageHandler;
import jsfaber.tileMapGenerator.handlers.KeyHandler;
import jsfaber.tileMapGenerator.handlers.MouseHandler;;

public class MapGenerator {
	
	private Map tileMap;
	
	private ImageHandler imgHand;
	
	private static int mode;
	
	//file I/O stuff
	FileWriter fw = null;
	BufferedWriter bw = null;

	
	
	public MapGenerator(Map tileMap) {
		//pass reference to tilemap
		this.tileMap = tileMap;
		
		//load representative images
		imgHand = new ImageHandler();
		
		mode = 0;
		
	}
	
	public void export() { //exports a file with the tilemap
		String outputString =  tileMap.toString();
		try{
			fw = new FileWriter("MapExports/testMap.txt");
			bw = new BufferedWriter(fw);
			bw.write(outputString);
			
			System.out.println("File Saved");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: in Mapgenerator.Export");
		}
		finally {
			try {
				if(bw != null) { bw.close(); }
				if(fw != null) { fw.close(); }
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getCurrentMode() {
		return mode;
	}
	
	public void update() { //responsible for handling changes in tilemap
		handleMouseInput();
		handleButtonInput();
		
	}
	
	public void draw(Graphics2D g) { //draws all the tiles
		for (int i = 0; i < tileMap.getNumTilesHeight(); i++) {
			for (int j = 0; j < tileMap.getNumTilesWidth(); j++) {
				g.drawImage(imgHand.getImage(0, tileMap.getTile(i, j)), 30* j, 30* i, null);
			}
		}
	}
	
	public void handleButtonInput() {
		if(KeyHandler.isPressed(KeyHandler.ZERO)) 		{ mode = 0; }
		else if(KeyHandler.isPressed(KeyHandler.ONE)) 	{ mode = 1; }
		else if(KeyHandler.isPressed(KeyHandler.TWO)) 	{ mode = 2; }
		else if(KeyHandler.isPressed(KeyHandler.THREE)) { mode = 3; }
		else if(KeyHandler.isPressed(KeyHandler.FOUR)) 	{ mode = 4; }
		else if(KeyHandler.isPressed(KeyHandler.FIVE)) 	{ mode = 5; }
		else if(KeyHandler.isPressed(KeyHandler.SIX)) 	{ mode = 6; }
		
		else if(KeyHandler.isPressed(KeyHandler.ENTER)) { export(); } 
	}
	
	public void handleMouseInput() {	
		if(MouseHandler.isHeld(MouseHandler.LEFT)) { 
			tileMap.changeTile(MouseHandler.getCurrentX() / 30, MouseHandler.getCurrentY() / 30, mode);
		}

	}
	
	
		
}

