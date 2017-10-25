/*
* Essential Computing 1 Assignment #5 - "Handle events on multiple buttons"
*                                + #6 - "Run through the minesweeper objects to find neighbouring objects"
* Jeppe S. Faber 59774
* ----------------------------------------------------------------------------------------------------------------------
* Class: FieldButton.java
*   FieldButton is a simple extension of Javafx control buttons designed to hold values relating to minesweeper logic as
* implemented in GameGrid.java. The purpose of FieldButton is to function as both a visual and abstract representation
* of a button used in a minesweeper array. FieldButton contains all necessary data-values relating to the minesweeper
* logic in GameGrid, in addition to standard javafx Button functionality.
*
*   FieldButton instances are state-based, using the enumerated type BtnState. BtnStates represent af combination of
* whether or not the button:  1. has been pressed, 2. is a bomb and 3. is flagged.
*/
package minesweeper;

import javafx.scene.control.Button;

public class FieldButton extends Button {

    //define button states.
    public enum BtnState{
        DEFAULT,    //!bomb, !flag, !pressed.
        FLAGGED,    //!bomb,  flag, !pressed.
        BOMBED,     // bomb, !flag, !pressed.
        DETECTED,   // bomb,  flag, !pressed.
        CLEARED,    //!bomb, !flag,  pressed.
        GAMEOVER    // bomb, !flag,  pressed
    }

    //define CSS-styles corresponding to states.
    public static final String DEFAULT = "-fx-background-color: #a9916c; -fx-border-color: black";
    public static final String FLAGGED = "-fx-background-color: #ffdb4d; -fx-border-color: black";
    public static final String BOMBED = "-fx-background-color: black"; //bombed & default shares style in final.
    //public static final String DETECTED = "-fx-background-color: aqua"; //detected & flagged shares style in final.
    public static final String CLEARED = "-fx-background-color: #ffd583; -fx-border-color: black";
    public static final String GAMEOVER = "-fx-background-color: #ae0000; -fx-border-color: black";

    private BtnState buttonState;
    private int bombCount = 0; //number of neighbouring bomb-tiles.
    private boolean cascaded;
    private int xLoc;
    private int yLoc;

    //constructor
    public FieldButton(int xLoc, int yLoc) {
        setState(BtnState.DEFAULT);
        cascaded = false;

        this.xLoc = xLoc;
        this.yLoc = yLoc;
    }

    //setters
    public void setState(BtnState state){
        buttonState = state;

        try {
            switch (state) {
                case DEFAULT:
                    setStyle(DEFAULT);
                    break;
                case FLAGGED:
                    setStyle(FLAGGED);
                    break;
                case BOMBED:
                    setStyle(DEFAULT);
                    setStyle(BOMBED); //Comment this out when not testing.
                    break;
                case DETECTED:
                    setStyle(FLAGGED);
                    break;
                case CLEARED:
                    setStyle(CLEARED);
                    //show the number of bomb neighbours only on cleared tiles.
                    if(bombCount > 0) {
                        setText("" + bombCount);
                    }
                    break;
                case GAMEOVER:
                    setStyle(GAMEOVER);
                    System.out.println("GAME OVER");
                    break;
                default:
                    throw new Exception("Unexpected Button State");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setCascaded(boolean value) {
        cascaded = value;
    }

    //getters
    public int getBombCount() { return bombCount; }

    public boolean isCascaded() { return cascaded; }

    public BtnState getState() { return buttonState; }

    public int getX() { return xLoc; }
    public int getY() { return yLoc; }

    //methods
    public void incrementBombCount(){
        if(buttonState == BtnState.DEFAULT){
            bombCount++;
        }
        else{ //else here is mostly to detect if something went wrong, ideally this 'else' is unecessary.
            bombCount = 99;
        }
    }
}
