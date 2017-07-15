package jsfaber.platformer1.gameState;

import java.awt.Graphics2D;

import jsfaber.platformer1.main.GamePanel;
import jsfaber.platformer1.image.ResourceImage;
import jsfaber.platformer1.handlers.KeyHandler;

public class MenuState extends GameState {
	
	//images
	final int N_OPTIONS = 3;
	ResourceImage background, title;
	ResourceImage[] option = new ResourceImage[N_OPTIONS]; 
	ResourceImage[] optionSel = new ResourceImage[N_OPTIONS];

	//selection variables
	private int currSelection = 1;

	private int textX;
	
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		//load selection
		currSelection = 0;
		textX = GamePanel.WIDTH / 10;
		
		//load background
		background = new ResourceImage("/Menu/BlackBG.gif", 0, 0);
		
		//load title
		title = new ResourceImage("/Menu/titleAsset.gif", 400, 100);
		
		//load unselected options
		for(int i = 0; i < 3; i++) {
			option[i] = new ResourceImage("/Menu/option" + (i+1) + "W.gif", textX , i*100 + 200);
		}
		
		//load selected options
		for(int i = 0; i < 3; i++) {
			optionSel[i] = new ResourceImage("/Menu/option" + (i+1) +  "R.gif", textX, i*100 + 200);
		}
	}
	
	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		//draw background
		background.draw(g);
		
		//draw title
		title.draw(g);

		//draw options
		for(int i = 0; i < 3; i++) {
			if(currSelection == i) {
				optionSel[i].draw(g);
			}
			else {
				option[i].draw(g);
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
	
	public void select(int selection) {
		switch(selection) {
		case 0: gsm.changeState(GameStateManager.LEVEL1STATE);
			break;
		case 1: gsm.changeState(GameStateManager.GAME_OVER_STATE);
			break;
		case 2: System.out.println("Game Closed"); 
				System.exit(0);
			break;
		}
	}
	
}
