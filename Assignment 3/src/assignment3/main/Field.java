package assignment3.main;

import java.util.Random;
import assignment3.utils.ArrayUtilities;

public class Field {

    private int numColumns;
    private int numRows;
    private Tile[][] mineField;
    private int numBombs;

    public Field(int numRows, int numColumns, int numBombs){
        this.numColumns = numRows;
        this.numRows = numColumns;
        mineField = new Tile[numColumns][numRows];
        if(numBombs < (numColumns * numRows)){  //We have to make sure the amount of bombs does not exceed the number of
            this.numBombs = numBombs;           // of available tiles. If it does, the program will loop infinitely.
        }                                       // see bomb-placement part of initField() for the reason why.
        else {
            this.numBombs = numRows;
        }
        initField();
    }

    //fills the minefield with bombs at random. This could be a part of the constructor.
    private void initField() {

        //initialize the minefield with tiles, and default every tile to be not a bomb.
        for(int yCoord = 0; yCoord < numRows; yCoord++){
            for(int xCoord = 0; xCoord < numColumns; xCoord++){
                mineField[yCoord][xCoord] = new Tile(yCoord, xCoord,false);
            }
        }

        Random randomGenerator = new Random();
        //track all previously generated bomb locations.
        int[] previousX = new int[numBombs];
        int[] previousY = new int[numBombs];

        //fill minefield with the set amount bombs, placed at random locations with no overlap.
        for(int bombCount = 0; bombCount < numBombs; bombCount++){
            //generate random coordinates for bombs.
            int generatedX = randomGenerator.nextInt(numColumns); //this returns a value between 0 and numColumns-1
            int generatedY = randomGenerator.nextInt(numRows);    //ditto(numRows)

            //check whether the new bomb has been generated before. If numBombs > available tiles, then the field will
            //fill out, this will always return false and the loop will continue infinitely.
             if(!ArrayUtilities.containsInt(generatedX, previousX) ||
                    !ArrayUtilities.containsInt(generatedY, previousY)){
                previousX[bombCount] = generatedX;
                previousY[bombCount] = generatedY;

                mineField[generatedY][generatedX].setBomb(true);
            }
            else {//if it has, try again.
                bombCount--;
            }
        }
    }

    //prints out mineField[][] into the console, with bonus ascii formatting, WOW!
    public void printFieldToConsole() {
        int columnWhiteSpace = ((numColumns *6)-1); //the amount of whitespace between each character.

        for(int i = 0; i < numRows; i++){

            //print top line of each row
            System.out.print("  +");
            for(int k = 0; k < columnWhiteSpace; k++) {
                System.out.print("-");
            }
            System.out.print("+\n");

            //print tile + spacers between each column in the row.
            for(int j = 0; j < numColumns; j++) {
                if (!mineField[i][j].isBomb()) {
                    System.out.print("  |  " + 0);
                } else {
                    System.out.print("  |  " + 1);
                }
            }
            System.out.print("  |\n");
        }

        //print ending line with corners
        System.out.print("  +");
        for(int k = 0; k < columnWhiteSpace; k++) {
            System.out.print("-");
        }
        System.out.print("+\n");
    }
}
