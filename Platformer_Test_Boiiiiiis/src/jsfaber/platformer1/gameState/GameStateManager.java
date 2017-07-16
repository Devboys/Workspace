package jsfaber.platformer1.gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import jsfaber.platformer1.main.GamePanel;

public class GameStateManager {
    
    //state variables
    public static final int N_STATES = 3;
    private int currentState;
    private GameState[] gameStates;
    
    //state representations, add new for each state you want to be possible
    public static final int MENU_STATE = 0;
    public static final int GAME_OVER_STATE = 1;
    public static final int LEVEL1STATE = 2;
    //--||--
    
    
    
    public GameStateManager() {
        gameStates = new GameState[N_STATES];
        
        currentState = MENU_STATE; //pull up the menu when you first create a gameStateManager / launch the game
        loadState(currentState);
    }
    
    private void loadState(int state) {
        switch(state) {
        case MENU_STATE: gameStates[state] = new MenuState(this);
            break;
        case GAME_OVER_STATE: gameStates[state] = new GameOverState(this);
            break;
        case LEVEL1STATE: gameStates[state] = new Level1State(this);
            break;
          }
    }
    
    private void unloadState(int state) {
        gameStates[state] = null;
    }
    
    public void changeState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }
    
    public void update() {
        if(gameStates[currentState] != null)gameStates[currentState].update();
        else { System.out.println("gsm.update(): No Gamestate in slot");
        }
    }
    
    public void draw(Graphics2D g) {
        if(gameStates[currentState] != null) gameStates[currentState].draw(g);
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            g.drawString("gsm.draw(g): No Gamestate in slot ", 20, 20);
        }
    }
    
}
