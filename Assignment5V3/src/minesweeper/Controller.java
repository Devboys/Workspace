/*
* Essential Computing 1 Assignment #5 - "Handle events on multiple buttons"
*                                + #6 - "Run through the minesweeper objects to find neighbouring objects"
* Jeppe S. Faber 59774
* ----------------------------------------------------------------------------------------------------------------------
* Class: Controller.java
*   Controller is the FXML controller for mainMenu.fxml. The point of Controller and mainMenu.fxml is to get input from
* the user and use this to set up the game. When a user has entered and confirmed the relevant data for the game-field
* size and the number of desired bombs, startGame() is executed, a GameGrid is set up and the stage-view is switched to
* the GameGrid object.
*/
package minesweeper;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    //FXML reference fields --------------------------------------------------------------------------------------------
    @FXML
    TextField bombCountInputField = new TextField();
    @FXML
    TextField heightInputField = new TextField();
    @FXML
    TextField widthInputField = new TextField();
    @FXML
    Label bombAmountLabel = new Label();
    @FXML
    Label tileAmountLabel = new Label();
    @FXML
    Label difficultyErrorLabel = new Label();
    @FXML
    Label startErrorLabel = new Label();
    @FXML
    Button startButton = new Button();

    //Java fields ------------------------------------------------------------------------------------------------------

    public static final int GAME_WIDTH = 750;
    public static final int GAME_HEIGHT = 750;

    boolean ready = false;

    public int numBombs;
    private int numColumns;
    private int numRows;


    public void startGame() {
        if(ready) {
            //reset error label. Should be inconsequential when program works.
            startErrorLabel.setText("YOU'RE NOT SUPPOSED TO SEE THIS");

            //create a GameGrid, with the given size, filled with the set amount of bombs at random locations.
            GameGrid gameGrid = new GameGrid(numBombs, numRows, numColumns);

            //switch to game-view.
            Stage currentStage = (Stage) startButton.getScene().getWindow(); //get a reference to the current stage
            Scene gameScene = new Scene(gameGrid, GAME_WIDTH, GAME_HEIGHT);
            currentStage.setScene(gameScene);
            currentStage.centerOnScreen();
        }
        else {
            startErrorLabel.setText("Please select a difficulty preset or input custom values");
        }
    }


    public void presetSelectEasy() {
        int numBombs = 15;
        int size = 10;
        setDifficulty(numBombs, size, size);
    }

    public void presetSelectMedium() {
        int numBombs = 20;
        int size = 12;
        setDifficulty(numBombs, size, size);
    }

    public void presetSelectHard() {
        int numBombs = 100;
        int size = 23;
        setDifficulty(numBombs, size, size);
    }

    public void confirmCustomDifficulty() {
        try{
            int numBombs = Integer.parseInt(bombCountInputField.getText());
            int numRows = Integer.parseInt(heightInputField.getText());
            int numColumns = Integer.parseInt(widthInputField.getText());
            setDifficulty(numBombs, numColumns, numRows);
        } catch(Exception e) {
            difficultyErrorLabel.setText("*INVALID INPUT*");
            bombAmountLabel.setText("NaN");
            tileAmountLabel.setText("NaN");
            ready = false;
        }

    }

    private void setDifficulty(int numBombs, int numColumns, int numRows) {
        //set class-scope variables.
        this.numBombs = numBombs;
        this.numColumns= numColumns;
        this.numRows = numRows;

        //change text to represent new values
        bombCountInputField.setText(Integer.toString(numBombs));
        widthInputField.setText(Integer.toString(numColumns));
        heightInputField.setText(Integer.toString(numRows));

        bombAmountLabel.setText(Integer.toString(numBombs));
        tileAmountLabel.setText(Integer.toString(numRows * numColumns));
        difficultyErrorLabel.setText("");

        ready = true;
    }
}
