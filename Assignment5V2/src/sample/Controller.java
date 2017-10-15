package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class Controller {
    //FXML references --------------------------------------------------------------------------------------------------
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

    public static final int[][] NEIGHBOURS = {
            {-1, -1}, {-1, 0 }, {-1, +1},
            { 0, -1},           { 0, +1},
            {+1, -1}, {+1, 0 }, {+1, +1}
    };

    public static final int GAME_WIDTH = 750;
    public static final int GAME_HEIGHT = 750;

    boolean ready = false;

    public static int numBombs; //BAD SOLUTION
    private int numColumns;
    private int numRows;

    private FieldButton[][] buttonGrid;


    public void startGame() { //called by start button.
        if(ready) {
            //reset error label. Should be inconsequential when program works.
            startErrorLabel.setText("");

            setupButtonGrid();

            //create a new GridPane, which will hold all buttons.
            GridPane gameGrid = new GridPane();
            gameGrid.setGridLinesVisible(true);
            gameGrid.setPadding(new Insets( 10));

            //setup the gameGrid to be able to hold all fields.
            for(int rowCounter = 0; rowCounter < numRows; rowCounter++) {
                gameGrid.addRow(rowCounter);
            }
            for(int columnCounter = 0; columnCounter < numColumns; columnCounter++) {
                gameGrid.addColumn(columnCounter);
            }

            //fill the rows and columns with buttons.
            for(int i = 0; i < numRows; i++) {
                for(int j = 0; j < numColumns; j++) {
                    gameGrid.add(buttonGrid[i][j], j, i);
                }
            }

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

    //sets up the buttonGrid by initializing every button with default state and adding bombs at random locations.
    private void setupButtonGrid() {

        //initialize each field in the button grid with default buttons.
        buttonGrid = new FieldButton[numRows][numColumns];
        for(int rowCounter = 0; rowCounter < numRows; rowCounter++){
            for(int columnCounter = 0; columnCounter < numColumns; columnCounter++){
                FieldButton theButton = new FieldButton();

                //set the preffered size of each button, so that all buttons are contained within the window.
                theButton.setPrefWidth(GAME_WIDTH /numColumns);
                theButton.setPrefHeight(GAME_HEIGHT /numRows);

                buttonGrid[rowCounter][columnCounter] = theButton;
            }
        }

        //put bombs at random locations on the board.
        Random randomGenerator  = new Random();
        for(int bombCounter = 0; bombCounter < numBombs; bombCounter++){

            //generate random position for bomb.
            int randomY = randomGenerator.nextInt(numRows);
            int randomX = randomGenerator.nextInt(numColumns);

            //make sure selected field is not already a bomb.
            if(buttonGrid[randomY][randomX].getState() != FieldButton.BtnState.BOMBED){
                buttonGrid[randomY][randomX].setState(FieldButton.BtnState.BOMBED);

                //now let the neighbouring fields know that they have a new bomb neighbour.
                for(int[] offset : NEIGHBOURS){

                    int neighbourY = randomY + offset[0];
                    int neighbourX = randomX + offset[1];

                    //Check if the neighbouring tile is not out of bounds.
                    if(   !(neighbourX < 0 ||
                            neighbourX > (numColumns-1) ||
                            neighbourY < 0 ||
                            neighbourY > (numRows-1))
                            ){
                        buttonGrid[neighbourY][neighbourX].incrementBombCount();
                    }
                }
            }
            else{//if the field was already a bomb, try again.
                bombCounter--;
            }
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
