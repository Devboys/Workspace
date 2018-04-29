package Mazes;

import Mazes.Backtracker.BacktrackerMaze;
import Mazes.Division.DivisionMaze;
import Mazes.Kruskal.KruskalMaze;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MazeTest {

    private static final int mazeSize = 10;

    @Before
    public void setUp() throws Exception {
        BacktrackerMaze bMaze = new BacktrackerMaze(mazeSize);
        KruskalMaze kMaze = new KruskalMaze(mazeSize);
        DivisionMaze dMaze = new DivisionMaze(mazeSize);
    }

    @Test void noLoopsInMaze(){
        //check for no loops how?
    }

    @Test void noClosedLoopsInMaze(){
        //
    }

    @Test void noInaccessibleAreasInMaze(){
        //check that solveable from each cell in maze to end
    }

    private void resetMaze(Maze maze){

    }
}