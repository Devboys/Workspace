package jsfaber.platformer1.gameState;

import java.awt.Graphics2D;

import jsfaber.platformer1.handlers.KeyHandler;
import jsfaber.platformer1.image.ResourceImage;
import jsfaber.platformer1.main.GamePanel;

public abstract class LevelState extends GameState {
    
    //pause menu
    private int textX;
    protected final int N_OPTIONS = 3;
    protected boolean paused;
    ResourceImage pauseBG;
    ResourceImage[] pausedOptionsSel;
    ResourceImage[] pausedOptionsUnsel;
    
    //selection variables
    int currSelection;
    
    public LevelState(GameStateManager gsm) {
        super(gsm);
        pauseinit();
    }
    
    public void pauseinit() {
        paused = false;
        currSelection = 0;
        textX = GamePanel.WIDTH / 10;
        pausedOptionsSel = new ResourceImage[N_OPTIONS];
        pausedOptionsUnsel = new ResourceImage[N_OPTIONS];
        try {
            pauseBG = new ResourceImage("/Pause/pauseBG.png", 0, 0);
            for(int i = 0; i < N_OPTIONS; i++) {
                pausedOptionsSel[i] = new ResourceImage("/Pause/selpauseOption" + (i+1) + ".gif", textX, i * 100 + 200);
                pausedOptionsUnsel[i] = new ResourceImage("/Pause/unselpauseOption" +(i+1)+ ".gif", textX, i * 100 + 200);
            }
        } catch(Exception e) { e.printStackTrace();}
    }
    
    public void pause() {
        paused = true;
        currSelection = 0;
        System.out.println("game paused");
    }
    
    public void unpause() {
        paused = false;
        System.out.println("game unpaused");
    }
    
    public void select(int selection) {
        switch(selection) {
        case 0: unpause();
            break;
        case 1: gsm.changeState(GameStateManager.MENU_STATE);
            break;
        case 2: System.out.println("Game Closed");
                System.exit(0);
            break;
        }
    }
    
    public void updatePauseMenu() {}
    
    public void drawPauseMenu(Graphics2D g) {
        
        //draw BG
        pauseBG.draw(g);
        
        //draw options
        for(int i = 0; i < N_OPTIONS; i++) {
            if(currSelection == i) {
                pausedOptionsSel[i].draw(g);
            }
            else {
                pausedOptionsUnsel[i].draw(g);
            }
        }
    }
    
    public void handlePauseInput() {
        if(KeyHandler.isPressed(KeyHandler.ESCAPE)) {
            unpause();
        }
        if(KeyHandler.isPressed(KeyHandler.UP)) {
            if(currSelection <= 0) currSelection = N_OPTIONS-1;
            else currSelection--;

        }
        if(KeyHandler.isPressed(KeyHandler.DOWN)) {
            if(currSelection >= N_OPTIONS - 1)currSelection= 0;
            else currSelection++;
        }
        if(KeyHandler.isPressed(KeyHandler.ENTER)) {
            select(currSelection);
        }
    }
    
    
}
