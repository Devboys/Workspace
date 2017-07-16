package jsfaber.tileMapGenerator.handlers;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class MouseHandler {
    
    private static int N_MOUSE_EVENTS = 1;
    
    private static boolean[] mouseStates = new boolean[N_MOUSE_EVENTS];
    private static boolean[] prevMouseStates = new boolean[N_MOUSE_EVENTS];
    
    //keys
    public static final int LEFT = 0;
    
    private static int x, y;
    
    public static void update(Point mouseLoc) {
        for(int i = 0; i < N_MOUSE_EVENTS; i++) {
            prevMouseStates[i] = mouseStates[i];
        }
        if(mouseLoc != null) {
            x = (int) (mouseLoc.getX());
            y = (int) (mouseLoc.getY());
        }
    }
    
    public static void mouseSet(int key, boolean state) {
        if(key == MouseEvent.BUTTON1) { mouseStates[LEFT] = state; }
        
    }
    
    public static boolean isPressed(int key) {
        return (mouseStates[key] && !prevMouseStates[key]);
    }
    
    public static boolean isHeld(int key) {
        return mouseStates[key];
    }
    
    public static int getCurrentX() { return x; }
    public static int getCurrentY() { return y; }
}
