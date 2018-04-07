package Mazes.Backtracker;

import Mazes.MazeThread;

public class BacktrackerThread extends MazeThread{

    public BacktrackerThread(int mazeSize, Object lock){
        maze = new BacktrackerMaze(mazeSize, lock);
    }
}
