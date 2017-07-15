package jsfaber.platformer1.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import jsfaber.platformer1.tileMap.Tile;
import jsfaber.platformer1.tileMap.TileMap;

public abstract class Entity {
	
	//tilemap
	protected TileMap tileMap;
	
	//tilemap location
	protected int xLoc;
	protected int yLoc;
	protected int xDest;
	protected int yDest;
	
	//movement vectors
	protected int dx;
	protected int dy;
	
	//collision box variables
	protected int cWidth; 
	protected int cHeight;
	protected Rectangle collisionBox;
	
	//collision check variables
	Tile upLeft;
	Tile upRight;
	Tile downLeft;
	Tile downRight;
	protected boolean cornerUpLeft;
	protected boolean cornerUpRight;
	protected boolean cornerDownLeft;
	protected boolean cornerDownRight;
	
	//motion variables
	protected boolean falling;
	
	
	public int getLocX() {
		return xLoc;
	}
	
	public int getLocY() {
		return yLoc;
	}
	
	public Rectangle getCBox() {
		return collisionBox;
	}
	
	public void calculateCorners(int x, int y) {
		
		upLeft = tileMap.getTileFromCoord(x - (cWidth / 2), y - (cHeight / 2));
		upRight = tileMap.getTileFromCoord(x + (cWidth / 2), y - (cHeight / 2));
		downLeft = tileMap.getTileFromCoord(x - (cWidth / 2), y + (cHeight / 2));
		downRight = tileMap.getTileFromCoord(x + (cWidth / 2), y + (cHeight / 2));
		
		cornerUpLeft = upLeft.isBlocked();
		cornerUpRight = upRight.isBlocked();
		cornerDownLeft = downLeft.isBlocked();
		cornerDownRight = downRight.isBlocked();
		
	}
	
	
	public void checkCollision() {
		
		if(dx >= tileMap.getTileSize()) dx = tileMap.getTileSize() - 1;
		else if(dx <= -tileMap.getTileSize()) dx = -tileMap.getTileSize() + 1;
		if(dy >= tileMap.getTileSize()) dy = tileMap.getTileSize() - 1;
		else if(dy <= -tileMap.getTileSize()) dy = -tileMap.getTileSize() + 1;
		
		xDest = xLoc + dx;
		yDest = yLoc + dy;
		
		//y-direction
		calculateCorners(xLoc, yDest); //calculate vertical collision
		if(dy < 0) { //if moving upward
			if(cornerUpLeft || cornerUpRight) { //if any upper corner is blocked, collide
				dy = 0;
				yDest = tileMap.getTileFromCoord(xLoc, yLoc).getTileUpperBound() + cHeight / 2 + 1;
			}
		}
		else if(dy > 0) { //if moving downward
			if(cornerDownLeft || cornerDownRight) { //if any lower corner is blocked, collide
				dy = 0;
				yDest = tileMap.getTileFromCoord(xLoc, yLoc).getTileLowerBound() - cHeight / 2 - 1;
			}
		}
		
		//x-direction
		calculateCorners(xDest, yLoc); //calculate horizontal collision
		if(dx < 0) { //if moving left
			if(cornerUpLeft || cornerDownLeft) {
				dx = 0;
				xDest = tileMap.getTileFromCoord(xLoc, yLoc).getTileLeftBound() + cWidth / 2 + 1;
			}
		}
		else if(dx > 0) { //if moving right
			if(cornerUpRight || cornerDownRight) {
				dx = 0;
				xDest = tileMap.getTileFromCoord(xLoc, yLoc).getTileRightBound() - cWidth / 2 - 1;
			}
		}
		
		
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
	
}
