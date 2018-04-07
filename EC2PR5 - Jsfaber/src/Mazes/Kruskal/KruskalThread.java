package Mazes.Kruskal;

import Mazes.MazeThread;

public class KruskalThread extends MazeThread {

    public KruskalThread(int size, Object lock) {
        maze = new KruskalMaze(size, lock);
    }
}

