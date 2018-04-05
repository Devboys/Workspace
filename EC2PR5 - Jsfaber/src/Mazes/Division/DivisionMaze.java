package Mazes.Division;

import Mazes.Cell;
import Mazes.Maze;

public class DivisionMaze extends Maze {

    public DivisionMaze(int size){
        super(size);

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = new DivisionCell();
            }
        }

        boolean vertical = rnd.nextBoolean();
        if(vertical)
            divideVertical(maze);
        else{
            divideHorizontal(maze);
        }
        fillEdges();

        startCell = assignRandomExitCell();
        startCell.setStart(true);
        endCell = assignRandomExitCell();
        endCell.setEnd(true);
    }

    private void divideHorizontal(Cell[][] region){

        int regionWidth = region.length;
        int regionHeight = region[0].length;

        //the division will produce two sub-regions.
        Cell[][] upperRegion;
        Cell[][] lowerRegion;

        //draw a random horizontal line(enable southern walls of all elements in a random row)
        int divisorRowIndex = rnd.nextInt(regionHeight);
        for(int i = 0; i < regionWidth; i++){
            region[i][divisorRowIndex].setSouthOpen(false);
        }

        //punch a hole in the recently created line.
        int holeIndex = rnd.nextInt(regionWidth);
        region[holeIndex][divisorRowIndex].setSouthOpen(true);

        //define new regions
        //upper region is everything from 0 to rowIndex.
        int upperWidth = regionWidth;
        int upperHeight = divisorRowIndex+1;
        upperRegion = new Cell[upperWidth][upperHeight];

        for(int i = 0; i < upperWidth; i++){
            for(int j = 0; j < upperHeight; j++){
                upperRegion[i][j] = region[i][j];
            }
        }

        //lower region is everything from rowindex+1 to height.
        int lowerWidth = regionWidth;
        int lowerHeight = regionHeight - upperHeight;
        lowerRegion = new Cell[lowerWidth][lowerHeight];

        for(int i = 0; i < lowerWidth; i++){
            for(int j = 0; j < lowerHeight; j++){
                lowerRegion[i][j] = region[i][j + upperHeight];
            }
        }

        //divide new regions vertically only if they have more than one column in them.
        if(lowerRegion[0].length > 1) divideVertical(lowerRegion);
        if(upperRegion[0].length > 1) divideVertical(upperRegion);
    }

    private void divideVertical(Cell[][] region){

        int regionWidth = region.length;
        int regionHeight = region[0].length;

        //the division will produce two sub-regions.
        Cell[][] leftRegion;
        Cell[][] rightRegion;

        //draw a random vertical line (enable eastern walls of all elements in a random column)
        int divisorColumnIndex = rnd.nextInt(regionWidth);
        for(int i = 0; i <  regionHeight; i++){
            region[divisorColumnIndex][i].setEastOpen(false);
        }

        //punch a hole in the recently created line
        int holeIndex = rnd.nextInt(regionHeight);
        region[divisorColumnIndex][holeIndex].setEastOpen(true);

        //define new regions
        //left region is everything from 0 to columnindex
        int leftWidth = divisorColumnIndex+1;
        int leftHeight = regionHeight;
        leftRegion = new Cell[leftWidth][leftHeight];
        for(int i = 0; i < leftWidth; i++){
            for(int j = 0; j < leftHeight; j++){
                leftRegion[i][j] = region[i][j];
            }
        }

        //right region is everything from columnindex+1 to width
        int rightWidth = regionWidth - leftWidth;
        int rightHeight = regionHeight;
        rightRegion = new Cell[rightWidth][rightHeight];
        for(int i = 0; i < rightWidth; i++){
            for(int j = 0; j < rightHeight; j++){
                rightRegion[i][j] = region[i + leftWidth][j];
            }
        }

        //divide region horizontally only if they have more than one row in them.
        if(leftRegion.length > 1) divideHorizontal(leftRegion);
        if(rightRegion.length > 1) divideHorizontal(rightRegion);
    }

    private void fillEdges(){
        //fill north edge
        for(int i = 0; i < width; i++){
            maze[i][0].setNorthOpen(false);
        }
        //fill south edge
        for(int i = 0; i < width; i++){
            maze[i][height-1].setSouthOpen(false);
        }

        //fill east edge
        for(int i = 0; i < height; i++){
            maze[width-1][i].setEastOpen(false);
        }

        //fill west edge
        for(int i = 0; i < height; i++){
            maze[0][i].setWestOpen(false);
        }
    }
}
