package Mazes.Kruskal;

import Mazes.Cell;
import Mazes.Maze;
import java.util.ArrayList;
import java.util.Random;

public class KruskalMaze implements Maze{

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    private KruskalCell[][] maze;
    private int width;
    private int height;
    Random rnd;

    private int numRoots;

    public KruskalMaze(int size){
        height = size;
        width = size;

        maze = new KruskalCell[width][height];
        rnd = new Random();
        numRoots = width * height;

        //initialize maze with default cells
        for(int i = 0; i < width; i++){
            for(int j =0; j < height; j++){
                maze[i][j] = new KruskalCell();
            }
        }
        generateMaze();
    }

    private void generateMaze(){
        // Kruskal's algorithm continues until the entire maze is a single set. In this implemenetation, the set is
        // identified by its root-node, so when the number of root-nodes is equal to 1, kruskals algorithm has finished.
        while(numRoots > 1){
            int currX = rnd.nextInt(width);
            int currY = rnd.nextInt(height);

            KruskalCell currCell = maze[currX][currY];
            Direction randomDirection = getRandomDirection(currX, currY);
            KruskalCell nextCell = getNeighbour(randomDirection, currX, currY);

            //check if the cells are in the same set.
            if(currCell.getRoot() != nextCell.getRoot()){
                switch (randomDirection){
                    case NORTH:
                        currCell.setNorth(true);
                        nextCell.setSouth(true);
                        break;
                    case SOUTH:
                        currCell.setSouth(true);
                        nextCell.setNorth(true);
                        break;
                    case EAST:
                        currCell.setEast(true);
                        nextCell.setWest(true);
                        break;
                    case WEST:
                        currCell.setWest(true);
                        nextCell.setEast(true);
                        break;
                }
                //merge the set of the current cell with the set of the neighbour cell.
                nextCell.getRoot().setPrevious(currCell);

                //each time we merge, we must reduce the number of roots.
                numRoots--;
            }
        }
    }


    /**
     * @param x the x position of the element from which directions must be considered.
     * @param y the y position of the element from which directions must be considered.
     * @return a random Direction within the bounds of the maze.
     */
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


    /**
     * @param direction the direction of the element to be returned.
     * @param x the x position of the element from which directions must be considered.
     * @param y the y position of the element from which directions must be considered.
     * @return the KrukskalCell element in the given direction from the given x,y coordinates in the maze.
     * @throws IndexOutOfBoundsException
     */
    private KruskalCell getNeighbour(Direction direction, int x, int y) throws IndexOutOfBoundsException{
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

    public Cell get(int x, int y){ return maze[x][y]; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
}
