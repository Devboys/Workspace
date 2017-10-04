package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller{
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
    //Code objects
    MinesweeperGrid minesweeperGrid = new MinesweeperGrid();
    boolean ready = false;

    public void startGame() {
        if(ready){
            startErrorLabel.setText("");
        }
        else{
            startErrorLabel.setText("Please select a difficulty preset or input custom values");
        }
    }

    //Draw the data from our minesweeperGrid as buttons
    public void drawField() {}


    public void presetSelectEasy() {
        int numBombs = 20;
        int size = 10;

        setDifficulty(numBombs, size, size);
    }

    public void presetSelectMedium() {
        int numBombs = 30;
        int size = 10;
        setDifficulty(numBombs, size, size);
    }

    public void presetSelectHard() {
        int numBombs = 40;
        int size = 10;
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

        minesweeperGrid.setNumBombs(numBombs);
        minesweeperGrid.setNumColumns(numColumns);
        minesweeperGrid.setNumRows(numRows);

        bombCountInputField.setText(Integer.toString(numBombs));
        widthInputField.setText(Integer.toString(numColumns));
        heightInputField.setText(Integer.toString(numRows));

        bombAmountLabel.setText(Integer.toString(numBombs));
        tileAmountLabel.setText(Integer.toString(numRows * numColumns));
        difficultyErrorLabel.setText("");
        ready = true;
    }


}
