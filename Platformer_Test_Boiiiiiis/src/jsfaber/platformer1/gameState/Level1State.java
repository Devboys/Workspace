package jsfaber.platformer1.gameState;

import java.awt.Graphics2D;

import jsfaber.platformer1.entity.Player;
import jsfaber.platformer1.handlers.KeyHandler;
import jsfaber.platformer1.image.ResourceImage;
import jsfaber.platformer1.tileMap.TileMap;

public class Level1State extends LevelState {
    
    ResourceImage bg, text;
    TileMap tileMap;
    Player player;
    
    public Level1State(GameStateManager gsm) {
        super(gsm);
        init();
    }
    
    public void init() {
        try {
            //load background
            bg = new ResourceImage("/Level1/BlackBG.gif", 0, 0);
            
            //load text
            text = new ResourceImage("/Level1/Level1W.gif", 0, 0);
        } catch(Exception e) { e.printStackTrace();}
        
        //load tileMap;
        tileMap = new TileMap("Level1", 30);
        player = new Player(tileMap, 120, 50, 20, 40);
        
    }
    
    public void update() {
        if(paused) {
            updatePauseMenu();
            handlePauseInput();
        }
        else {
            handleInput();
            tileMap.update();
            player.update();
        }
    }
    
    public void draw(Graphics2D g) {
        
        bg.draw(g);
        text.draw(g);
        
        tileMap.draw(g);
        player.draw(g);
        
        if(paused) {
            drawPauseMenu(g);
        }
        
//        //test start
//        g.setColor(new Color(155,155,155));
//        g.fillRect(120, 410, 30, 40);
//        //test end
        
    }
    
    public void handleInput() {
        player.handleInput();
        if(KeyHandler.isPressed(KeyHandler.ESCAPE)) {
                pause();
        }
        
    }
}
