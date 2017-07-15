package jsfaber.platformer1.handlers;

import java.awt.event.KeyEvent;

//handler class for all keypresses used, to add a new keyPress, add a new static int under KEYS
//and a new line in keySet()
public class KeyHandler {
	
	public static final int NUM_KEYS = 6;
	
	public static boolean[] keyState = new boolean[NUM_KEYS];
	public static boolean[] prevKeyState = new boolean[NUM_KEYS];
	
	//KEYS
	public static final int UP = 0;  //Selection screen up
	public static final int DOWN = 1; //selection screen down
	public static final int ENTER = 2; //selection confirm
	public static final int ESCAPE = 3; //escape for pause
	public static final int LEFT = 4;
	public static final int RIGHT = 5;
	
	public static void keySet(int key, boolean b) {
		switch(key) {
		case KeyEvent.VK_UP: keyState[UP] = b;
			break;
		case KeyEvent.VK_W: keyState[UP] = b;
			break;
		case KeyEvent.VK_DOWN: keyState[DOWN] = b;
			break;
		case KeyEvent.VK_S: keyState[DOWN] = b;
			break;
		case KeyEvent.VK_ENTER: keyState[ENTER] = b;
			break;
		case KeyEvent.VK_ESCAPE: keyState[ESCAPE] = b;
			break;
		case KeyEvent.VK_LEFT: keyState[LEFT] = b;
			break;
		case KeyEvent.VK_A: keyState[LEFT] = b;
			break;
		case KeyEvent.VK_RIGHT: keyState[RIGHT] = b;
			break;
		case KeyEvent.VK_D: keyState[RIGHT] = b;
			break;
		}
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int key) {
		return (keyState[key] && !prevKeyState[key]); 
	}
	
	public static boolean isReleased(int key) {
		return (!keyState[key] && prevKeyState[key]);
	}
	
	public static boolean isHeld(int key) {
		return keyState[key];
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}
}
