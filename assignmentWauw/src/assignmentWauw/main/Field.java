package assignmentWauw.main;

import java.util.Random;
import assignmentWauw.utils.ArrayUtilities;

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

    private void initField() {
        double numEmptycells = numColumns * numRows;
        double numUnassignedBombs = numBombs;

        Random randomGen = new Random();

        for(int rowCounter = 0; rowCounter < numRows; rowCounter++) {
            for(int columnCounter = 0; columnCounter < numColumns; columnCounter++) {
                mineField[rowCounter][columnCounter] = new Tile();

                int chance = (int) ((numUnassignedBom / numEmptycells) * 100);
                if(randomGen.nextInt(100) <= chance) {
                    mineField[rowCounter][columnCounter].setBomb(true);
                }
                else {
                    mineField[rowCounter][columnCounter].setBomb(false);
                }
                numEmptycells--;
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
