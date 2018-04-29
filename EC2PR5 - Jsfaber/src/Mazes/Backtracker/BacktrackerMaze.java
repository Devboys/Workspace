package Mazes.Backtracker;

import Mazes.Cell;
import Mazes.Maze;

import java.util.ArrayList;

public class BacktrackerMaze extends Maze {

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        DEFAULT //see switch in digTile() for usage
    }

    private int initX, initY;

    protected BacktrackerMaze(int size, Object lock){
        this(size, 0, 0, lock);
    }
    protected BacktrackerMaze(int size, int x, int y, Object lock){
        super(size, lock);
        maze = new Cell[width][height];

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = new Cell();
            }
        }

        initX = x;
        initY = y;
        stepLock = lock;
    }

    public BacktrackerMaze(int size){
        this(size, 0, 0);
    }
    public BacktrackerMaze(int size, int x, int y){
        super(size);
        maze = new Cell[width][height];

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = new Cell();
            }
        }

        initX = x;
        initY = y;
        try {
            generate();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**Generates the maze using recursive backtracker. The generation will call wait() on the declared lock between each
     * step, and will require a notify() to continue.
     * @throws InterruptedException */
    @Override public void generate() throws InterruptedException{
        digTile(initX, initY);

        startCell = assignRandomExitCell();
        startCell.setStart(true);
        endCell = assignRandomExitCell();
        endCell.setEnd(true);

        //reset visited so that it can be re-used for solving.
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j].setVisited(false);
            }
        }

        finished = true;
    }

    /**Implementation of recursive-backtracker to generate the currently available maze.
     * Every step is separated by a wait() call to the current thread on whichever object-lock was
     * passed to the constructor.
     * @param x the x-index of the cell to be handled
     * @param y the y-index of the cell to be handled.
     * @throws InterruptedException */
    private void digTile(int x, int y) throws InterruptedException {
        Cell currentCell = maze[x][y];
        currentCell.setVisited(true);

        ArrayList<Direction> availableDirections;

        boolean neighboursAvailable = true;

        if(isStepable()) {
            synchronized (stepLock) {
                stepLock.wait();
            }
        }
        while (neighboursAvailable) {
            //availableDirections holds all available directions from the cell at the given position.
            availableDirections = getAvailableCells(x, y);

            Direction nextDirection = Direction.DEFAULT;
            if (availableDirections.size() != 0) {
                nextDirection = availableDirections.get(rnd.nextInt(availableDirections.size()));
            }
            switch (nextDirection) {
                case NORTH:
                    Cell northCell = maze[x][y - 1];
                    availableDirections.remove(nextDirection);
                    currentCell.setNorthOpen(true);
                    northCell.setSouthOpen(true);


                    digTile(x, y - 1);
                    break;
                case SOUTH:
                    Cell southCell = maze[x][y + 1];
                    availableDirections.remove(nextDirection);
                    currentCell.setSouthOpen(true);
                    southCell.setNorthOpen(true);

                    digTile(x, y + 1);
                    break;
                case WEST:
                    Cell westCell = maze[x - 1][y];
                    availableDirections.remove(nextDirection);
                    currentCell.setWestOpen(true);
                    westCell.setEastOpen(true);

                    digTile(x - 1, y);
                    break;
                case EAST:
                    Cell eastCell = maze[x + 1][y];
                    availableDirections.remove(nextDirection);
                    currentCell.setEastOpen(true);
                    eastCell.setWestOpen(true);

                    digTile(x + 1, y);
                    break;
                case DEFAULT:
                    //if nextDirection is default, it must mean that no valid directions are available.
                    neighboursAvailable = false;
                    break;
            }
        }
    }

    /**@param x the x-index of the current cell
     * @param y the y-index of the current cell
     * @return an arrayList of all directions that lead to unvisited cells, in regards to the current cells index. */
    private ArrayList<Direction> getAvailableCells(int x, int y){
        ArrayList<Direction> returnArray = new ArrayList<Direction>(4);

        //NORTH
        try {
            Cell northCell = maze[x][y - 1];
            if (northCell.isNotVisited()) {
                returnArray.add(Direction.NORTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //SOUTH
        try {
            Cell southCell =  maze[x][y + 1];
            if (southCell.isNotVisited()) {
                returnArray.add(Direction.SOUTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //EAST
        try {
            Cell eastCell = maze[x + 1][y];
            if (eastCell.isNotVisited()) {
                returnArray.add(Direction.EAST);
            }
        }catch(IndexOutOfBoundsException e){}

        //WEST
        try {
            Cell westCell = maze[x - 1][y];
            if (westCell.isNotVisited()) {
                returnArray.add(Direction.WEST);
            }
        }catch (IndexOutOfBoundsException e){}
        return returnArray;
    }

}
