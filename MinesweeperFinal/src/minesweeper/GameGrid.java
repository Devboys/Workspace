/*
* Essential Computing Mini Project

* Jeppe S. Faber 59774
* ----------------------------------------------------------------------------------------------------------------------
* Class: GameGrid.java
*   The GameGrid class holds references to all FieldButton objects in a minesweeper session and handles all necessary
* minesweeper game-logic and mouse-input. GameGrid extends GridPane, and can therefore be used as a Javafx scene root.
* GameGrid is instantiated in Controller after the necessary input has been given(field size and number of bombs).
*/
package minesweeper;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class GameGrid extends GridPane {

    //array used to check neighbours of a specific tile.
    private static final int[][] NEIGHBOURS = {
            {-1, -1}, {-1, 0 }, {-1, +1},
            { 0, -1},           { 0, +1},
            {+1, -1}, {+1, 0 }, {+1, +1}
    }; //Neighbour-offset array taken from user: Joshiah Krutz on codereview.stackexchange.com
       //thread: Checking for neighbours more elegantly in Conway's Game of Life.

    private int numRows;
    private int numColumns;
    private int numBombs;
    private int numBombsDetected; //keeps track of the total number of user-detected bombs.
    private int numFieldsCorrect; //tracks total number of fields either cleared or detected.
    private boolean gameOver;

    //shared EventHandler for every button. Better solutions probably exist, but to track game over-status, this works.
    EventHandler<MouseEvent> theHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (!gameOver) {
                FieldButton theButton = (FieldButton) event.getSource();

                //handle left-click input.
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (theButton.getState() == FieldButton.BtnState.DEFAULT) {
                        theButton.setState(FieldButton.BtnState.CLEARED);
                    }
                    else if (theButton.getState() == FieldButton.BtnState.BOMBED) {
                        theButton.setState(FieldButton.BtnState.GAMEOVER);
                        System.out.println("Oops, you lose.");
                        gameOver = true;

                        //indicate the game is lost by setting all tiles to lose states.
                        for(int i = 0; i < numRows; i++) {
                            for(int j = 0; j < numColumns; j++) {
                                gridMap[i][j].setState(FieldButton.BtnState.LOSE);
                            }
                        }
                    }
                    cascade(theButton);
                }

                //handle Right-click input
                else if (event.getButton().equals(MouseButton.SECONDARY)) {

                    //if bomb, flag to FLAGGED. Does not count toward win.
                    if (theButton.getState() == FieldButton.BtnState.DEFAULT) {
                        theButton.setState(FieldButton.BtnState.FLAGGED);
                    }
                    //if bomb, flag to DETECTED
                    else if (theButton.getState() == FieldButton.BtnState.BOMBED) {
                        theButton.setState(FieldButton.BtnState.DETECTED);
                        numBombsDetected++;
                        numFieldsCorrect++;
                    }
                    //if flagged, return to DEFAULT
                    else if (theButton.getState() == FieldButton.BtnState.FLAGGED) {
                        theButton.setState(FieldButton.BtnState.DEFAULT);
                    }
                    //if detected, return to BOMBED
                    else if (theButton.getState() == FieldButton.BtnState.DETECTED) {
                        theButton.setState(FieldButton.BtnState.BOMBED);
                        numBombsDetected--;
                        numFieldsCorrect--;
                    }
                }

                //console output keeps track of how many fields are left.
                System.out.println(numFieldsCorrect + " / " + numRows * numColumns);

                //game is won when all bombs have been detected and all other fields have been revealed.
                if(numBombsDetected == numBombs && numFieldsCorrect == numRows*numColumns) {
                    System.out.println("You Win!");
                    gameOver = true;

                    //indicate the game is won, by setting all states to win states.
                    for(int i = 0; i < numRows; i++) {
                        for(int j = 0; j < numColumns; j++) {
                            gridMap[i][j].setState(FieldButton.BtnState.WIN);
                            gridMap[i][j].setText("");
                        }
                    }
                }
            }
            else { //if game is over, next click will close game through the stage.
                Stage currentStage = (Stage) getScene().getWindow();
                currentStage.close();
            }

        }
    };

    //the gridMap, used to hold all button references inserted into the GameGrid.
    FieldButton[][] gridMap;

    public GameGrid(int numBombs, int numRows, int numColumns) {

        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numBombs = numBombs;
        numBombsDetected = 0;
        numFieldsCorrect = 0;
        gameOver = false;

        //setup the gameGrid to the given size.
        for(int rowCounter = 0; rowCounter < numRows; rowCounter++) {
            this.addRow(rowCounter);
        }
        for(int columnCounter = 0; columnCounter < numColumns; columnCounter++) {
            this.addColumn(columnCounter);
        }

        setupGridMap();

        //use the gridMap to fill the GameGrid with buttons.
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++) {
                this.add(gridMap[i][j], j, i);
            }
        }
    }

    //sets up the gridMap by initializing every button with default state and adding bombs at random locations.
    private void setupGridMap() {
        gridMap = new FieldButton[numRows][numColumns];

        //initialize each field in the gridMap with default buttons.
        gridMap = new FieldButton[numRows][numColumns];
        for(int rowCounter = 0; rowCounter < numRows; rowCounter++){
            for(int columnCounter = 0; columnCounter < numColumns; columnCounter++){
                FieldButton theButton = new FieldButton(columnCounter, rowCounter);

                //set the preferred size of each button, such that all buttons fit within the window.
                theButton.setPrefWidth(Controller.GAME_WIDTH / numColumns);
                theButton.setPrefHeight(Controller.GAME_HEIGHT / numRows);

                theButton.setOnMouseClicked(theHandler);

                gridMap[rowCounter][columnCounter] = theButton;
            }
        }
        placeBombs();
    }

    //put bombs at random locations on the board.
    private void placeBombs() {
        Random randomGenerator  = new Random();
        for(int bombCounter = 0; bombCounter < numBombs; bombCounter++){

            //generate random position for bomb.
            int randomY = randomGenerator.nextInt(numRows);
            int randomX = randomGenerator.nextInt(numColumns);

            //make sure selected field is not already a bomb.
            if(gridMap[randomY][randomX].getState() != FieldButton.BtnState.BOMBED){
                gridMap[randomY][randomX].setState(FieldButton.BtnState.BOMBED);

                //let the neighbouring fields know that they have a new bomb neighbour.
                for(int[] offset : NEIGHBOURS){
                    int neighbourY = randomY + offset[0];
                    int neighbourX = randomX + offset[1];

                    //Check if the neighbouring tile is not out of bounds.
                    if(   !(neighbourX < 0 ||
                            neighbourX > (numColumns-1) ||
                            neighbourY < 0 ||
                            neighbourY > (numRows-1))
                            ){
                        gridMap[neighbourY][neighbourX].incrementBombCount();
                    }
                }
            }
            else{//if the field was already a bomb, try again.
                bombCounter--;
            }
        }
    }

    //reveal nearby neighbours if they are not near bombs.
    private void cascade(FieldButton theButton) {

        //cascade only non-bombed or flagged fields and prevent already cascaded fields from being cascaded again.
        if(    (theButton.getState() == FieldButton.BtnState.DEFAULT ||
                theButton.getState() == FieldButton.BtnState.CLEARED) && !theButton.isCascaded()) {

            theButton.setCascaded(true);
            theButton.setState(FieldButton.BtnState.CLEARED);
            numFieldsCorrect++;

            for(int[] offset : NEIGHBOURS){

                int neighbourY = theButton.getY() + offset[0];
                int neighbourX = theButton.getX() + offset[1];

                //make sure neighbour field is not out of bounds.
                if(   !(neighbourX < 0 ||
                        neighbourX > (numColumns-1) ||
                        neighbourY < 0 ||
                        neighbourY > (numRows-1))
                        ){
                    if(theButton.getBombCount() == 0) {
                        //cascade next tile.
                        cascade(gridMap[neighbourY][neighbourX]);
                    }
                }
            }
        }
    }
}
