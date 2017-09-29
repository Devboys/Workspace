package assignment3.main;

import java.util.Random;
import assignment3.utils.ArrayUtilities;

public class Field {

    private int numRows;
    private int numColumns;
    private Tile[][] mineField;
    private int numBombs;

    public Tile[][] getField() { return mineField};

    public Field(int numRows, int numColumns, int numBombs){
        this.numRows = numRows;
        this.numColumns = numColumns;
        mineField = new Tile[numColumns][numRows];
        this.numBombs = numBombs;
        initField();
    }

    //fills the minefield with bombs at random. This could be a part of the constructor.
    private void initField() {

        //initialize the minefield with tiles, and default every tile to be not a bomb.
        for(int yCoord = 0; yCoord < numColumns; yCoord++){
            for(int xCoord = 0; xCoord < numRows; xCoord++){
                mineField[yCoord][xCoord] = new Tile(yCoord, xCoord,false);
            }
        }

        //fill minefield with the set amount bombs, placed at random locations with no overlap.
        Random randomGenerator = new Random();
        for(int bombCount = 0; bombCount < numBombs; bombCount++){

            //generate random coordinates for bombs.
            int generatedX = randomGenerator.nextInt(numRows-1);
            int generatedY = randomGenerator.nextInt(numColumns-1);

            //hold all previously generated bomb locations.
            int[] previousX = new int[numBombs];
            int[] previousY = new int[numBombs];

            //check whether the new bomb has been generated before.
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


}
