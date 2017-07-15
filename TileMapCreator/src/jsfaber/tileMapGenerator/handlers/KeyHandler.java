package jsfaber.tileMapGenerator.handlers;

import java.awt.event.KeyEvent;

public class KeyHandler {
	
	private static final int N_KEYS = 8;
	
	private static boolean[] keyStates = new boolean[N_KEYS];
	private static boolean[] prevKeyStates = new boolean[N_KEYS];
	
	//keys
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int ENTER = 7;
	
	
	public static void keySet(int key, boolean state) {
		switch(key) {
		case KeyEvent.VK_0: keyStates[ZERO] = state;
			break;
		case KeyEvent.VK_1: keyStates[ONE] = state;
			break;
		case KeyEvent.VK_2: keyStates[TWO] = state;
			break;
		case KeyEvent.VK_3: keyStates[THREE] = state;
			break;
		case KeyEvent.VK_4: keyStates[FOUR] = state;
			break;
		case KeyEvent.VK_5: keyStates[FIVE] = state;
			break;
		case KeyEvent.VK_6: keyStates[SIX] = state;
			break;
		case KeyEvent.VK_ENTER: keyStates[ENTER] = state;
			break;
		default: 
			if( state == true) {
				System.out.println(
						"KeyHandler: input not within parameters"
					);}
			break;
		}
	}
	
	public static void update() {
		for(int i = 0; i < N_KEYS; i++) {
			prevKeyStates[i] = keyStates[i];
		}
	}
	
	public static boolean isPressed(int key) {
		return (keyStates[key] && !prevKeyStates[key]); 
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < N_KEYS; i++) {
			if(keyStates[i]) return true;
		}
		return false;
	}
}
	
