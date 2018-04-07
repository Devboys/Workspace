package Mazes.Division;

import Mazes.MazeThread;

public class DivisionThread extends MazeThread{

    public DivisionThread(int size, Object lock){
        maze =  new DivisionMaze(size, lock);
    }
}
