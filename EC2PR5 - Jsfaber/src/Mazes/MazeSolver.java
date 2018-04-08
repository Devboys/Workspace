package Mazes;

import java.util.ArrayList;
import java.util.Random;

public class MazeSolver {

    private Maze maze;

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        DEFAULT
    }
    private static Random rnd;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        rnd = new Random();
    }

    public void solve(){
        depthFirstSolve(maze.getStartX(), maze.getStartY());
    }

    /**Recursive implementation of depth-first search for finding a path between the start and endpoints of a maze.
     * @param x the x-index of the current cell being considered.
     * @param y the y-index of the current cell being considered.
     * @return whether or not the current cell leads to a maze end-point
     */
    private boolean depthFirstSolve(int x, int y){
        Cell currentCell =  maze.get(x, y);
        currentCell.setVisited(true);

        if(currentCell.isEnd()) {
            return true;
        }

        ArrayList<Direction> traversableDirections;

        boolean endHasBeenFound = false;
        boolean neighboursAvailable = true;
            while (neighboursAvailable && !endHasBeenFound) {
                //availableDirections holds all traversable directions from the cell at the given position.
                traversableDirections = getTraversableDirections(x, y);

                Direction nextDirection = Direction.DEFAULT;
                if (traversableDirections.size() != 0) {
                    nextDirection = traversableDirections.get(rnd.nextInt(traversableDirections.size()));
                }

                switch (nextDirection) {
                    case NORTH:
                        traversableDirections.remove(nextDirection);
                        endHasBeenFound = depthFirstSolve(x, y - 1);
                        break;
                    case SOUTH:
                        traversableDirections.remove(nextDirection);
                        endHasBeenFound = depthFirstSolve( x, y + 1);
                        break;
                    case WEST:
                        traversableDirections.remove(nextDirection);
                        endHasBeenFound = depthFirstSolve(x - 1, y);
                        break;
                    case EAST:
                        traversableDirections.remove(nextDirection);
                        endHasBeenFound = depthFirstSolve(x + 1, y);
                        break;
                    case DEFAULT:
                        //if nextDirection is default, it must mean that no valid directions are available.
                        neighboursAvailable = false;
                        break;
                }
            }

            //if any of the traversable directions lead to the end point, the current cell must be on the path to end.
            if(endHasBeenFound) currentCell.setPath(true);
            return endHasBeenFound;
        }

    /**Returns an array of traversable directions from the current cell at the given index.
     * A direction is only traversable if there are no walls in the direction, and the cell in the direction has not yet
     * been visited.
     * @param x the x-index of the current cell.
     * @param y the y-index of the current cell.
     * @return an ArrayList containing all traversable directions from the current cell.*/
    private ArrayList<Direction> getTraversableDirections(int x, int y){
        ArrayList<Direction> returnArray = new ArrayList<Direction>(4);
        Cell currCell = maze.get(x, y);

        //NORTH
        try {
            Cell northCell = maze.get(x, y - 1);
            if (    northCell.isNotVisited() &&
                    northCell.isSouthOpen() &&
                    currCell.isNorthOpen()
                    ){
                returnArray.add(Direction.NORTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //SOUTH
        try {
            Cell southCell =  maze.get(x,y + 1);
            if (    southCell.isNotVisited() &&
                    southCell.isNorthOpen() &&
                    currCell.isSouthOpen()
                    ){
                returnArray.add(Direction.SOUTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //EAST
        try {
            Cell eastCell = maze.get(x + 1, y);
            if (    eastCell.isNotVisited() &&
                    eastCell.isWestOpen() &&
                    currCell.isEastOpen()
                    ){
                returnArray.add(Direction.EAST);
            }
        }catch(IndexOutOfBoundsException e){}

        //WEST
        try {
            Cell westCell = maze.get(x - 1, y);
            if (    westCell.isNotVisited() &&
                    westCell.isEastOpen() &&
                    currCell.isWestOpen()
                    ){
                returnArray.add(Direction.WEST);
            }
        }catch (IndexOutOfBoundsException e){}
        return returnArray;
    }
}
