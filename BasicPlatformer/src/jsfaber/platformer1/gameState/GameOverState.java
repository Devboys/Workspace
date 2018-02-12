package jsfaber.platformer1.gameState;

import java.awt.Graphics2D;

import jsfaber.platformer1.handlers.KeyHandler;
import jsfaber.platformer1.image.ResourceImage;
import jsfaber.platformer1.main.GamePanel;

public class GameOverState extends GameState {
    
    private int textX;
    private final int N_OPTIONS = 3;
    ResourceImage bg, title;
    ResourceImage[] optionsUnsel;
    ResourceImage[] optionsSel;
    
    //selection variables
    int currSelection;
    
    public GameOverState(GameStateManager gsm) {
        super(gsm);
        
        init();
    }
    
    public void init() {
        currSelection = 0;
        textX = GamePanel.WIDTH / 10;
        optionsUnsel = new ResourceImage[N_OPTIONS];
        optionsSel = new ResourceImage[N_OPTIONS];
        try{
            //load background
            bg = new ResourceImage("/GameOver/BlackBG.gif", 0, 0);
            //load title
            title = new ResourceImage("/GameOver/GameOverW.gif", 0, 0);
            //load unselected options
            for(int i = 0; i < N_OPTIONS; i++) {
                optionsUnsel[i] = new ResourceImage("/GameOver/option" + (i + 1 + "W.gif"), textX, i*100 + 200);
            }
            //load selected options
            for(int i = 0; i < N_OPTIONS; i++) {
                optionsSel[i] = new ResourceImage("/GameOver/option" + (i + 1 + "R.gif"), textX, i * 100 + 200);
            }
        } catch(Exception e) { e.printStackTrace(); }
    }
    
    public void select(int selection) {
        if(selection == 0) {
            gsm.changeState(GameStateManager.LEVEL1STATE);
        }
        if(selection == 1) {
            gsm.changeState(GameStateManager.MENU_STATE);
        }
    }
    
    public void update() {
        handleInput();
    }
    
    public void draw(Graphics2D g) {
        //draw BG
        bg.draw(g);
        //draw title
        title.draw(g);
        //draw options
        for(int i = 0; i < N_OPTIONS; i++) {
            if(currSelection == i) {
                optionsSel[i].draw(g);
            }
            else {
                optionsUnsel[i].draw(g);
            }
        }
    }
    
    public void handleInput() {
        if(KeyHandler.isPressed(KeyHandler.UP)) {
            if(currSelection <= 0) currSelection = N_OPTIONS - 1;
            else currSelection--;
        }
        if(KeyHandler.isPressed(KeyHandler.DOWN)) {
            if(currSelection >= N_OPTIONS - 1) currSelection = 0;
            else currSelection++;
        }
        if(KeyHandler.isPressed(KeyHandler.ENTER)) {
            select(currSelection);
        }
        
    }
    
}
