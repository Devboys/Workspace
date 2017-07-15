package jsfaber.platformer1.tileMap;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import jsfaber.platformer1.handlers.ImageLoader;

public class TileMap {
	
	private ArrayList<ArrayList<Tile>> tileMap;
	private int tileSize;
	
	//loading stuff
	private int[][] tileMapHolder;
	private BufferedReader bf = null;
	private FileReader fr = null;
	private String folderName;
	
	//image
	ImageLoader imgLoad;
	
	
	public TileMap(String levelIdentification, int tileSize) {
		tileMap = new ArrayList<ArrayList<Tile>>();
		folderName = levelIdentification;
		this.tileSize = tileSize;
		loadTileMap();
		
	}
	
	public void loadTileMap() { //loads tilemap from filename
		//first, load map into string
		String inputString = "";
		try{
			fr = new FileReader("TileMaps/" + folderName + ".txt");
			bf = new BufferedReader(fr);
			inputString = bf.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(bf != null) { bf.close(); }
				if(fr != null) { fr.close(); }
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//now split string twice and convert to a 2d int-array
		String[] splitString = inputString.split("<>");
		String[][] doubleSplit = new String[splitString.length][splitString[0].split("/").length];
		tileMapHolder = new int[doubleSplit.length][doubleSplit[0].length];
		for(int i = 0; i < doubleSplit.length; i++) {
			doubleSplit[i] = splitString[i].split("/");
			for (int j = 0; j < doubleSplit[i].length; j++) {
				tileMapHolder[i][j] = Integer.parseInt(doubleSplit[i][j]);
			}
		}
		//finally convert int-tilemap into Tile-tilemap
		for(int i = 0; i < tileMapHolder.length; i++) {
			tileMap.add(new ArrayList<Tile>());
			for(int j = 0; j < tileMapHolder[i].length; j++) {
				tileMap.get(i).add(new Tile(tileMapHolder[i][j], tileSize, j, i));
			}
		}
	}
	
	public Tile getTileUp(Tile currentTile) {
		int xIndex = currentTile.getXIndex();
		int yIndex = currentTile.getYIndex();
		if( yIndex == 0) {
			return tileMap.get(yIndex).get(xIndex);
		}
		return tileMap.get(yIndex - 1).get(xIndex);
	}
	
	public Tile getTileDown(Tile currentTile) {
		int xIndex = currentTile.getXIndex();
		int yIndex = currentTile.getYIndex();
		if( yIndex >= tileMap.size()) {
			return tileMap.get(yIndex).get(xIndex);
		}
		else {
			return tileMap.get(yIndex + 1).get(xIndex);
		}
	}
	
	public Tile getTileLeft(Tile currentTile) {
		int xIndex = currentTile.getXIndex();
		int yIndex = currentTile.getYIndex();
		if( xIndex == 0) {
			return tileMap.get(yIndex).get(xIndex);
		}
		return tileMap.get(yIndex).get(xIndex - 1);
	}
	
	public Tile getTileRight(Tile currentTile) {
		int xIndex = currentTile.getXIndex();
		int yIndex = currentTile.getYIndex();
		if( xIndex == tileMap.get(yIndex).size()) {
			return tileMap.get(yIndex).get(xIndex);
		}
		return tileMap.get(yIndex).get(xIndex + 1);
	}
	
	public Tile getTileFromCoord(int x, int y) {
		int yIndex = y / tileSize;
		int xIndex = x / tileSize;
		if(yIndex >= tileMap.size()) {
			yIndex = tileMap.size() - 1;
		}
		else if(yIndex < 0) {
			yIndex = 0;
		}
		if(xIndex >= tileMap.get(yIndex).size()) {
			xIndex = tileMap.get(yIndex).size() - 1;
		}
		else if(xIndex < 0) {
			xIndex = 0;
		}
		return tileMap.get(yIndex).get(xIndex);
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public Tile getTest() {
		return tileMap.get(0).get(0);
	}
	
	public void update() {}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < tileMap.size(); i++) {
			for(int j = 0; j < tileMap.get(i).size(); j++) {
				if(tileMap.get(i).get(j).getTextureType() > 0) {
					g.drawImage(ImageLoader.Level1Tiles[0][tileMap.get(i).get(j).getTextureType() - 1], j * tileSize, i * tileSize, null);
				}
			}
		}
	}
}
