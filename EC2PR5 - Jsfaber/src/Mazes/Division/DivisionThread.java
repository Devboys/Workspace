package Mazes.Division;

import Mazes.MazeThread;

/**Creates a maze using recursive division in a separate thread, to allow for step-by-step generation.
 * Use getMaze() to get a reference to the current DivisionMaze object. */
public class DivisionThread extends MazeThread{

    public DivisionThread(int size, Object lock){
        maze =  new DivisionMaze(size, lock);
    }
}
