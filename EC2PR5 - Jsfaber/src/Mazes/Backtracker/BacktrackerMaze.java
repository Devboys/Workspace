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
        DEFAULT
    }

    private int initX, initY;
    private Object stepLock;

    public BacktrackerMaze(int size, Object lock){
        this(size, 0, 0, lock);
    }

    public BacktrackerMaze(int size, int x, int y, Object lock){
        super(size);
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

    public void generate() throws InterruptedException{
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

    //Recursive-backtracker algorithm.
    private void digTile(int x, int y) throws InterruptedException {
        Cell currentCell = maze[x][y];
        currentCell.setVisited(true);

        ArrayList<Direction> availableDirections;

        boolean neighboursAvailable = true;

        synchronized (stepLock) {
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

                        stepLock.wait();
                        digTile(x, y - 1);
                        break;
                    case SOUTH:
                        Cell southCell = maze[x][y + 1];
                        availableDirections.remove(nextDirection);
                        currentCell.setSouthOpen(true);
                        southCell.setNorthOpen(true);

                        stepLock.wait();
                        digTile(x, y + 1);
                        break;
                    case WEST:
                        Cell westCell = maze[x - 1][y];
                        availableDirections.remove(nextDirection);
                        currentCell.setWestOpen(true);
                        westCell.setEastOpen(true);

                        stepLock.wait();
                        digTile(x - 1, y);
                        break;
                    case EAST:
                        Cell eastCell = maze[x + 1][y];
                        availableDirections.remove(nextDirection);
                        currentCell.setEastOpen(true);
                        eastCell.setWestOpen(true);

                        stepLock.wait();
                        digTile(x + 1, y);
                        break;
                    case DEFAULT:
                        //if nextDirection is default, it must mean that no valid directions are available.
                        neighboursAvailable = false;
                        break;
                }
            }
        }
    }

    //Returns an ArrayList of Directions to all neighbouring non-visited cells.
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
