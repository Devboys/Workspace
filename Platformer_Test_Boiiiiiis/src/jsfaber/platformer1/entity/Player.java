package jsfaber.platformer1.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import jsfaber.platformer1.handlers.ImageLoader;
import jsfaber.platformer1.handlers.KeyHandler;
import jsfaber.platformer1.tileMap.TileMap;

public class Player extends Entity {
	
	
	public Player(TileMap tileMap, int xLoc, int yLoc, int cWidth, int cHeight) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.cWidth = cWidth;
		this.cHeight = cHeight;
		this.tileMap = tileMap;
		collisionBox = new Rectangle(xLoc - cWidth / 2, yLoc - cWidth / 2, xLoc, yLoc);
		
		init();
	}
	
	public void init() {
		//this is mostly for test purposes
		dx = 0;
		dy = 0;
		
	}
	
	public void applyGravity() {
		dy += 1;
	}
	
	public void jump() {
	}
	
	public void increaseFall() {
		dy += 1;
	}
	
	@Override
	public void update() {
		checkCollision();

		xLoc = xDest;
		yLoc = yDest;
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(ImageLoader.PlayerSheet[0][0], xLoc - cWidth / 2, yLoc - cHeight / 2, null);
		g.setColor(new Color(255, 13, 255));
		g.drawLine(xLoc, yLoc, xLoc, yLoc);
	}

	public void handleInput() {
		if(KeyHandler.isPressed(KeyHandler.UP)) {
			dy -= 3;
		}
		if(KeyHandler.isPressed(KeyHandler.DOWN)) {
			dy += 3;
		}
		if(KeyHandler.isPressed(KeyHandler.LEFT)) {
			dx -= 3;
		}
		if(KeyHandler.isPressed(KeyHandler.RIGHT)) {
			dx += 3;
		}
		if(KeyHandler.isReleased(KeyHandler.LEFT)) {
			dx = 0;
		}
		if(KeyHandler.isReleased(KeyHandler.RIGHT)) {
			dx = 0;
		}
		if(KeyHandler.isReleased(KeyHandler.UP)) {
			dy = 0;
		}
		if(KeyHandler.isReleased(KeyHandler.DOWN)) {
			dy = 0;
		}
		
	}
}
