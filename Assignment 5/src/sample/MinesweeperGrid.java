package sample;

import java.util.Random;

public class MinesweeperGrid {

    private int numColumns;
    private int numRows;
    private Tile[][] tileGrid;
    private int numBombs;

    //creates a minesweeper field with the given size and with the given number of bombs placed randomly.
    public MinesweeperGrid(int numRows, int numColumns, int numBombs){

        //pass constructor values to class-scope variables.
        this.numColumns = numRows;
        this.numRows = numColumns;

        if(numBombs <= (numColumns * numRows)){ //Make sure the amount of bombs does not exceed the number of available
            this.numBombs = numBombs;           //tiles. If it does, the generator will loop infinitely.
        }
        else {
            this.numBombs = numRows * numColumns;
        }

        //in this constructor, we have all we need, so we can simply generate a grid.;
        generateGrid();
    }

    //this empty constructor allows us to create the grid before we have values to be given.
    public MinesweeperGrid() {}

    //setters
    public void setNumColumns(int numColumns) { this.numColumns = numColumns ; }
    public void setNumRows(int numRows) { this.numRows = numRows; }
    public void setNumBombs(int numBombs) { this.numBombs = numBombs; }

    //in this iteration, the tileGrid initialization has been separated from the constructor to allow us to call it
    public void generateGrid() {
        //initialize the boolean array with non-bomb tiles.
        tileGrid = new Tile[numColumns][numRows];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numColumns; j++){
                tileGrid[i][j] = new Tile(i, j, false);
            }
        }

        //fill minefield with the set amount bombs, placed at random locations with no overlap.
        Random randomGenerator = new Random();
        for(int bombCount = 0; bombCount < numBombs; bombCount++){

            //generate random coordinates the current bomb.
            int generatedX = randomGenerator.nextInt(numColumns); //this returns a value between 0 and numColumns-1.
            int generatedY = randomGenerator.nextInt(numRows);    //ditto(numRows).

            //make sure the random field is not already a bomb. If entire board is filled, this will loop forever.
            if(!tileGrid[generatedY][generatedX].getBombStatus()){
                tileGrid[generatedY][generatedX].setBombStatus(true);
            }
            else {//if it is, try again.
                bombCount--;
            }
        }
    }

    //prints out mineField[][] into the console, with bonus ascii formatting, WOW!
    public void printToConsole() {
        int columnWhiteSpace = ((numColumns *6)-1); //the amount of whitespace between each character.
                                                    //6 spaces between each letter, -1 for the last character on a line.
        for(int i = 0; i < numRows; i++){

            //print top line of each row
            System.out.print("  +");
            for(int k = 0; k < columnWhiteSpace; k++) {
                System.out.print("-");
            }
            System.out.print("+\n");

            //print tile + spacers between each column-element in the row.
            for(int j = 0; j < numColumns; j++) {
                if (!tileGrid[i][j].hasBeenPressed()) {    //not pressed = blank space.
                    System.out.print("  |  " + " ");
                }
                else if(!tileGrid[i][j].getBombStatus()) { //pressed, but no bomb = line.
                    System.out.print("  |  " + "-");
                }
                else {
                    System.out.print("  |  " + "x");        //pressed and bomb = cross.
                }
            }
            System.out.print("  |\n");
        }

        //print ending line with corners.
        System.out.print("  +");
        for(int k = 0; k < columnWhiteSpace; k++) {
            System.out.print("-");
        }
        System.out.print("+\n");
    }
}
