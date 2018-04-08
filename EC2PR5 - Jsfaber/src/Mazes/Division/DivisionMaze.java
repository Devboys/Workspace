package Mazes.Division;

import Mazes.Cell;
import Mazes.Maze;

public class DivisionMaze extends Maze {

    private Object stepLock;

    protected DivisionMaze(int size, Object lock){
        super(size);

        stepLock = lock;

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = new DivisionCell();
            }
        }
    }

    /**Generates the maze using recursive divisor. The generation will call wait() on the declared lock between each
     * division, and will require a notify() to continue.
     * @throws InterruptedException*/
    public void generate() throws InterruptedException{
        divide(maze);

        fillEdges();

        startCell = assignRandomExitCell();
        startCell.setStart(true);
        endCell = assignRandomExitCell();
        endCell.setEnd(true);

        finished = true;
    }

    /**Generates a maze using the recursive division algorithm
     * @param region
     * @throws InterruptedException
     */
    private void divide(Cell[][] region) throws InterruptedException{
        //wait before each step.
        synchronized (stepLock){ stepLock.wait(); }

        int regionWidth = region.length;
        int regionHeight = region[0].length;

        //region height/width comparison idea from: Jamis Buck, at weblog.jamisbuck.org.
        boolean isVertical = (regionWidth > regionHeight);

        //choose divisor line (any field but the southernmost / easternmost, since that was previous divisor line)
        int divisorIndex = (isVertical) ? rnd.nextInt(regionWidth-1) : rnd.nextInt(regionHeight-1);
        for(int i = 0; i < (isVertical ? regionHeight : regionWidth); i++){
            if(isVertical) region[divisorIndex][i].setEastOpen(false);
            else region[i][divisorIndex].setSouthOpen(false);
        }

        //punch hole
        int holeIndex = isVertical ? rnd.nextInt(regionHeight) : rnd.nextInt(regionWidth);
        if(isVertical) region[divisorIndex][holeIndex].setEastOpen(true);
        else region[holeIndex][divisorIndex].setSouthOpen(true);

        //define left / upper region.
        int r1Width = isVertical ? divisorIndex+1 : regionWidth;
        int r1Height = isVertical ? regionHeight : divisorIndex+1;
        Cell[][] r1 = new Cell[r1Width][r1Height];
        for(int i = 0; i < r1Width; i++){
            for(int j = 0; j < r1Height; j++){
                r1[i][j] = region[i][j];
            }
        }

        //define right / lower region
        int r2Width = isVertical ? regionWidth - r1Width : regionWidth;
        int r2Height = isVertical ? regionHeight : regionHeight - r1Height;
        Cell[][] r2 = new Cell[r2Width][r2Height];
        for(int i = 0; i < r2Width; i++){
            for(int j = 0; j < r2Height; j++){
                if(isVertical) r2[i][j] = region[i + r1Width][j];
                else r2[i][j] = region[i][j + r1Height];
            }
        }

        //divide each region as its own field only if it there are enough cells for it to be divided.
        if(r1Width > 1 && r1Height > 1) divide(r1);
        if(r2Width > 1 && r2Height > 1) divide(r2);
    }

    /**Fills all outer walls of the maze with walls.*/
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
