package Mazes.Backtracker;

import Mazes.MazeThread;

/**Creates a maze using recursive backtracker in a separate thread, to allow for step-by-step generation.
 * Use getMaze() to get a reference to the current BacktrackerMaze object */
public class BacktrackerThread extends MazeThread{

    public BacktrackerThread(int mazeSize, Object lock){
        maze = new BacktrackerMaze(mazeSize, lock);
    }
}
