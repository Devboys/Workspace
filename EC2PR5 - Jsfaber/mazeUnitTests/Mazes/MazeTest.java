package Mazes;

import Mazes.Backtracker.BacktrackerMaze;
import Mazes.Division.DivisionMaze;
import Mazes.Kruskal.KruskalMaze;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MazeTest {

    private static final int MAZE_SIZE = 10;
    private static final int NUM_CELLS = MAZE_SIZE * MAZE_SIZE;
    private static final int NUM_TESTS = 100;

    private BacktrackerMaze bMaze;
    private KruskalMaze kMaze;
    private DivisionMaze dMaze;

    private int numSteps;


    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST;
    }

    @Before
    public void setUp() {
        bMaze = new BacktrackerMaze(MAZE_SIZE);
        kMaze = new KruskalMaze(MAZE_SIZE);
        dMaze = new DivisionMaze(MAZE_SIZE);
        numSteps = 0;
    }

    @Test
    public void noInaccessibleAreasInBacktrackerMaze(){
        for (int i = 0; i < NUM_TESTS; i++) {
            setUp();
            InaccessibleTester(0, 0, bMaze, Direction.NORTH );
            Assert.assertEquals(numSteps, NUM_CELLS);
        }
    }

    @Test
    public void noInaccessibleAreasInDivisionMaze(){
        for (int i = 0; i <NUM_TESTS ; i++) {
            setUp();
            InaccessibleTester(0, 0, dMaze, Direction.NORTH );
            Assert.assertEquals(numSteps, NUM_CELLS);
        }
    }

    @Test
    public void noInaccessibleAreasInKruskalMaze(){
        for (int i = 0; i < NUM_TESTS; i++) {
            setUp();
            InaccessibleTester(0, 0, kMaze, Direction.NORTH );
            Assert.assertEquals(numSteps, NUM_CELLS);
        }
    }

    @Test
    public void noLoopsInBacktrackerMaze(){
        for (int i = 0; i < NUM_TESTS; i++){
            setUp();
            LoopTester(0, 0, bMaze, Direction.NORTH);
        }
    }

    @Test
    public void noLoopsInKruskalMaze(){
        for (int i = 0; i < NUM_TESTS; i++){
            setUp();
            LoopTester(0, 0, kMaze, Direction.NORTH);
        }
    }

    @Test
    public void noLoopsInDivisionMaze(){
        for (int i = 0; i < NUM_TESTS; i++){
            setUp();
            LoopTester(0, 0, dMaze, Direction.NORTH);
        }
    }


    //Recursive backtracker algorithm that explores all cells that can be reached from its start point and counts them.
    //If the amount of reachable cells  are not equal to the amount of cells in the maze, there must be an inacessible
    //field or set of fields.
    private void InaccessibleTester(int x, int y, Maze maze, Direction prevDirection){
        numSteps++;
        ArrayList<Direction> availableDirections = getTraversableDirections(x, y, maze);
        availableDirections.remove(prevDirection);

        for(int i = 0; i < availableDirections.size(); i++){
            switch (availableDirections.get(i)) {
                case NORTH:
                    InaccessibleTester(x, y-1, maze, Direction.SOUTH);
                    break;
                case SOUTH:
                    InaccessibleTester(x, y+1, maze, Direction.NORTH);
                    break;
                case WEST:
                    InaccessibleTester(x-1, y, maze, Direction.EAST);
                    break;
                case EAST:
                    InaccessibleTester(x+1, y, maze, Direction.WEST);
                    break;
            }
        }
    }

    //Recursive backtracker algorithm that explores all cells in the maze and counts them. If at any point the amount of
    //steps taken exceed the amount of cells in the maze, there must be a loop in the maze. Because of this, we must
    //assert this after every check to avoid infinite looping.
    private void LoopTester(int x, int y, Maze maze, Direction prevDirection){
        numSteps++;
        Assert.assertTrue(numSteps <= NUM_CELLS);
        ArrayList<Direction> availableDirections = getTraversableDirections(x, y, maze);
        availableDirections.remove(prevDirection);

        for(int i = 0; i < availableDirections.size(); i++){
            switch (availableDirections.get(i)) {
                case NORTH:
                    InaccessibleTester(x, y-1, maze, Direction.SOUTH);
                    break;
                case SOUTH:
                    InaccessibleTester(x, y+1, maze, Direction.NORTH);
                    break;
                case WEST:
                    InaccessibleTester(x-1, y, maze, Direction.EAST);
                    break;
                case EAST:
                    InaccessibleTester(x+1, y, maze, Direction.WEST);
                    break;
            }
        }
    }

    //utility method used in both types of checkers that checks which directions are traversable from cell at (x,y).
    private ArrayList<Direction> getTraversableDirections(int x, int y, Maze maze){
        ArrayList<Direction> returnArray = new ArrayList<Direction>(4);
        Cell currCell = maze.get(x, y);

        //NORTH
        try {
            Cell northCell = maze.get(x, y - 1);
            if (northCell.isSouthOpen() && currCell.isNorthOpen()){
                returnArray.add(Direction.NORTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //SOUTH
        try {
            Cell southCell =  maze.get(x,y + 1);
            if (southCell.isNorthOpen() && currCell.isSouthOpen()){
                returnArray.add(Direction.SOUTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //EAST
        try {
            Cell eastCell = maze.get(x + 1, y);
            if (eastCell.isWestOpen() && currCell.isEastOpen()){
                returnArray.add(Direction.EAST);
            }
        }catch(IndexOutOfBoundsException e){}

        //WEST
        try {
            Cell westCell = maze.get(x - 1, y);
            if (westCell.isEastOpen() && currCell.isWestOpen()){
                returnArray.add(Direction.WEST);
            }
        }catch (IndexOutOfBoundsException e){}
        return returnArray;
    }

}