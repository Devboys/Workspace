package sample;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class FieldButton extends Button {

    private static int numBombsDetected = 0;

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
    public static final String FLAGGED = "-fx-background-color: yellow; -fx-border-color: black";
    //public static final String BOMBED = "-fx-background-color: black"; //bombed & default shares style in final.
    //public static final String DETECTED = "-fx-background-color: aqua"; //detected & flagged shares style in final.
    public static final String CLEARED = "-fx-background-color: #ffd583; -fx-border-color: black";
    public static final String GAMEOVER = "-fx-background-color: red";

    private BtnState buttonState = BtnState.DEFAULT;

    private int bombCount = 0; //number of neighbouring bomb-tiles.

    //constructor
    public FieldButton() {

        setState(BtnState.DEFAULT);
        setStyle(DEFAULT);

        //handle button events using lambda expressions.
        setOnMouseClicked((MouseEvent e) -> {

            if(e.getButton().equals(MouseButton.PRIMARY) && buttonState == BtnState.DEFAULT) {
                setState(BtnState.CLEARED);

                //show the number of bomb neighbours only on cleared tiles.
                if(bombCount > 0) {
                    setText("" + bombCount);
                }
            }

            else if(e.getButton().equals(MouseButton.PRIMARY) && buttonState == BtnState.BOMBED) {
                setState(BtnState.GAMEOVER);

                System.out.println("GAME OVER");
            }

            if(e.getButton().equals(MouseButton.SECONDARY) && buttonState == BtnState.DEFAULT) {
                setState(BtnState.FLAGGED);
            }

            else if(e.getButton().equals(MouseButton.SECONDARY) && buttonState == BtnState.BOMBED) {
                setState(BtnState.DETECTED);
                numBombsDetected++;
                if(numBombsDetected == Controller.numBombs) { //BAD SOLUTION
                    System.out.println("you win!");
                }
            }

            else if(e.getButton().equals(MouseButton.SECONDARY) && buttonState == BtnState.FLAGGED) {
                setState(BtnState.DEFAULT);
            }

            else if(e.getButton().equals(MouseButton.SECONDARY) && buttonState == BtnState.DETECTED) {
                setState(BtnState.BOMBED);
            }
        });

    }

    public void setState(BtnState state){ //returns true if gameOver.
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
                    break;
                case DETECTED:
                    setStyle(FLAGGED);
                    break;
                case CLEARED:
                    setStyle(CLEARED);
                    break;
                case GAMEOVER:
                    setStyle(GAMEOVER);
                    break;
                default:
                    throw new Exception("Unexpected Button State");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void incrementBombCount(){
        if(buttonState == BtnState.DEFAULT){
            bombCount++;
        }
        else{ //else here is mostly to detect if something went wrong, ideally this 'else' is unecessary.
            bombCount = 99;
        }
    }

    public int getBombCount()   { return bombCount; }
    public BtnState getState()  { return buttonState; }
}
