package sample;

import java.util.Random;

public class MinesweeperGrid {

    public static final int[][] NEIGHBOURS = {
            {-1, -1}, {-1, 0 }, {-1, +1},
            { 0, -1},           { 0, +1},
            {+1, -1}, {+1, 0 }, {+1, +1}
    };

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

                //let the neighbouring tiles know that they have a bomb neighbour.
                for(int[] offset : NEIGHBOURS){
                    //Make sure the considered tile is not out of bounds
                    int neighbourX = generatedX + offset[1];
                    int neighbourY = generatedY + offset[0];
                    if(!(neighbourX < 0 || neighbourX > (numColumns-1) ||
                            neighbourY < 0 || neighbourY > (numRows-1))
                            ){
                        tileGrid[neighbourY][neighbourX].incrementNumNeighbouringBombs();
                    }
                }
            }
            else {//if it is, try again.
                bombCount--;
            }
        }
    }

    public Tile[][] getTileGrid() { return tileGrid; }
    public int getNumColumns() { return numColumns; }
    public int getNumRows() { return numRows; }

    public Tile getTile(int yLoc, int xLoc) {
        return tileGrid[yLoc][xLoc];
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
                if(!tileGrid[i][j].getBombStatus()) { //pressed, but no bomb = line.
                    System.out.print("  |  " + tileGrid[i][j].getNumBombNeighbours());
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
