package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
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

    GridPane gameGridPane = new GridPane();

    Button[][] buttons;

    //Variables and java objects
    MinesweeperGrid minesweeperGrid;

    EventHandler theHandler = new EventHandler() {
        @Override
        public void handle(Event event) {
            buttonPressed((Button)event.getSource());
        }
    };

    private int numBombs;
    private int numColumns;
    private int numRows;

    boolean ready = false;

    public static final int GAME_WIDTH = 750;
    public static final int GAME_HEIGHT = 750;

    public void startGame() throws IOException{
        if(ready){
            //reset error label
            startErrorLabel.setText("");

            //setup the tilemap
            minesweeperGrid = new MinesweeperGrid(numRows, numColumns, numBombs);
            minesweeperGrid.printToConsole();

            //setup button array
            buttons = new Button[numRows][numColumns];
            for(int i = 0; i < numRows; i++) {
                for(int j = 0; j < numColumns; j++) {
                    buttons[i][j] = new Button();
                    //set the pref size of each button, so that all are contained within the window
                    buttons[i][j].setPrefSize(GAME_WIDTH / numColumns, GAME_HEIGHT / numRows);

                    //set the
                    buttons[i][j].setId(i + "B" + j);
                    buttons[i][j].setOnAction(theHandler);
                }
            }

            //switch view to the game window.
            Stage currentStage = (Stage) startButton.getScene().getWindow(); //get a reference to the current stage
            GridPane gameRoot = new GridPane();
            gameRoot.setGridLinesVisible(true);
            Scene gameScene = new Scene(gameRoot, GAME_WIDTH, GAME_HEIGHT);
            currentStage.setScene(gameScene);
            currentStage.centerOnScreen();


            //setup new grid with a fitting number of columns and rows.
            for(int rowCounter = 0; rowCounter < minesweeperGrid.getNumRows(); rowCounter++) {
                gameRoot.addRow(rowCounter);
            }
            for(int columnCounter = 0; columnCounter < minesweeperGrid.getNumColumns(); columnCounter++) {
                gameRoot.addColumn(columnCounter);
            }

            //now add buttons to the grid
            for(int i = 0; i < numRows; i++) {
                for(int j = 0; j < numColumns; j++) {
                    gameRoot.add(buttons[i][j],j, i);
                }
            }
        }
        else{
            startErrorLabel.setText("Please select a difficulty preset or input custom values");
        }

    }

    public void buttonPressed(Button input) {
        //split the Id into the button coordinates.
        String[] parts = input.getId().split("B");
        int rowIndex = Integer.parseInt(parts[0]);
        int columnIndex = Integer.parseInt(parts[1]);

        ArrayList<int[]> neighbours = new ArrayList<>();
        for(int i = 0; i < MinesweeperGrid.NEIGHBOURS.length; i++) {
            int[] thisOne = MinesweeperGrid.NEIGHBOURS[i];
            neighbours.add(thisOne);
        }

        buttonPressed(rowIndex, columnIndex, neighbours);
    }

    public void buttonPressed(int rowIndex, int columnIndex, ArrayList<int[]> neighbours){
        Tile theTile = minesweeperGrid.getTile(rowIndex, columnIndex);
        Button theButton = buttons[rowIndex][columnIndex];

        //if the clicked button is not a bomb, change its icon.
        if(!theTile.getBombStatus()) {
            theButton.setText(Integer.toString(theTile.getNumBombNeighbours()));

            //cascade effect
            if(theTile.getNumBombNeighbours() == 0) {
                for (int[] offset : neighbours) {
                    int consideredX = columnIndex + offset[1];
                    int consideredY = rowIndex + offset[0];

                    if (!(consideredX < 0 || consideredX > (numColumns - 1) || consideredY < 0 || consideredY > (numRows - 1))) {
                        neighbours = new ArrayList<>();
                        for(int[] test: MinesweeperGrid.NEIGHBOURS) {
                            if(test != offset) {
                                neighbours.add(test);
                            }
                        }
                        buttonPressed(consideredY, consideredX, neighbours);
                    }
                }
            }
        }
        else {
            theButton.setText("X");
            System.out.println("BOOM!");
        }

    }

    public void expandClick() {

    }

    public void presetSelectEasy() {
        int numBombs = 5;
        int size = 10;
        setDifficulty(numBombs, size, size);
    }

    public void presetSelectMedium() {
        int numBombs = 15;
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
