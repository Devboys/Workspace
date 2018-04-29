package Mazes.Kruskal;

import Mazes.Cell;
import Mazes.Maze;

import java.util.ArrayList;

public class KruskalMaze extends Maze {

    private int numRoots;

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    protected KruskalMaze(int size, Object lock){
        super(size, lock);

        maze = new KruskalCell[width][height];
        numRoots = width * height;

        //initialize maze with default cells
        for(int i = 0; i < width; i++){
            for(int j =0; j < height; j++){
                maze[i][j] = new KruskalCell();
            }
        }
    }

    public KruskalMaze(int size){
        super(size);

        maze = new KruskalCell[width][height];
        numRoots = width * height;

        //initialize maze with default cells
        for(int i = 0; i < width; i++){
            for(int j =0; j < height; j++){
                maze[i][j] = new KruskalCell();
            }
        }

        try {
            generate();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**Generates the maze using Kruskals algorithm. The generation will call wait() on the declared lock between each
     * division, and will require a notify() to continue.*/
    @Override public void generate() throws InterruptedException {
        generateMaze();

        startCell = assignRandomExitCell();
        startCell.setStart(true);
        endCell = assignRandomExitCell();
        endCell.setEnd(true);

        finished = true;
    }

    /**Generates a maze using Kruskal's algorithm. Every step is separated by a wait() call to the current thread on
     * whichever object-lock was passed to the constructor.*/
    private void generateMaze() throws InterruptedException{

        // Kruskal's algorithm continues until the entire maze is a single set. In this implemenetation, the set is
        // identified by its root-node, so when the number of root-nodes is equal to 1, kruskals algorithm has finished.
        while(numRoots > 1){
            int currX = rnd.nextInt(width);
            int currY = rnd.nextInt(height);

            KruskalCell currCell = (KruskalCell) maze[currX][currY];
            Direction randomDirection = getRandomDirection(currX, currY);
            KruskalCell nextCell = (KruskalCell) getNeighbour(randomDirection, currX, currY);

            //check if the cells are in the same set.
            if(currCell.getRoot() != nextCell.getRoot()){
                //wait before each step is taken.
                if(isStepable()) {
                    synchronized (stepLock) { stepLock.wait(); }
                }

                switch (randomDirection){
                    case NORTH:
                        currCell.setNorthOpen(true);
                        nextCell.setSouthOpen(true);
                        break;
                    case SOUTH:
                        currCell.setSouthOpen(true);
                        nextCell.setNorthOpen(true);
                        break;
                    case EAST:
                        currCell.setEastOpen(true);
                        nextCell.setWestOpen(true);
                        break;
                    case WEST:
                        currCell.setWestOpen(true);
                        nextCell.setEastOpen(true);
                        break;
                }
                //merge the set of the current cell with the set of the neighbour cell.
                nextCell.getRoot().setPrevious(currCell);

                //each time we merge, we must reduce the number of roots.
                numRoots--;
            }
        }
    }

    /**@param x the x position of the element from which directions must be considered.
     * @param y the y position of the element from which directions must be considered.
     * @return a random Direction within the bounds of the maze. */
    private Direction getRandomDirection(int x, int y) {
        ArrayList<Direction> availableDirections = new ArrayList<>(Direction.values().length);

        for(Direction d :  Direction.values()){
            try{
                getNeighbour(d, x, y);

                //if exception is caught, the direction is not added.
                availableDirections.add(d);
            } catch (IndexOutOfBoundsException e){}
        }

        int directionIndex = rnd.nextInt(availableDirections.size());
        return availableDirections.get(directionIndex);
    }

    /**@param direction the direction of the element to be returned.
     * @param x the x position of the element from which directions must be considered.
     * @param y the y position of the element from which directions must be considered.
     * @return the KrukskalCell element in the given direction from the given x,y coordinates in the maze.
     * @throws IndexOutOfBoundsException */
    private Cell getNeighbour(Direction direction, int x, int y) throws IndexOutOfBoundsException{
        switch (direction){
            case NORTH:
                return maze[x][y-1];
            case SOUTH:
                return maze[x][y+1];
            case EAST:
                return maze[x+1][y];
            case WEST:
                return maze[x-1][y];
            default:
                return null;
        }
    }
}
