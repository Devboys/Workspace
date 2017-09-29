package assignment3.main;

import java.util.Random;

public class Field {

    private int numRows;
    private int numColumns;
    private Tile[][] mineField;
    private int numBombs;

    public Field(int numRows, int numColumns, int numBombs){
        this.numRows = numRows;
        this.numColumns = numColumns;
        mineField = new Tile[numColumns][numRows];
        this.numBombs = numBombs;
    }


    private void fillField() {

        //initialize the minefield with tiles, and default every tile to be not a bomb.
        for(int yCoord = 0; yCoord < numColumns; yCoord++){
            for(int xCoord = 0; xCoord < numRows; xCoord++){
                mineField[yCoord][xCoord] = new Tile(yCoord, xCoord,false);
            }
        }

        //fill minefield with the set amount bombs, placed at random with no duplicates.
        Random randomGenerator = new Random();
        for(int bombCount = 0; bombCount < numBombs; bombCount++){
            int generatedX = randomGenerator.nextInt();
            int generatedY = randomGenerator.nextInt();
            if()

        }
    }
}
