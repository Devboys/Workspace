package Mazes.Kruskal;

import Mazes.MazeThread;

/**Creates a maze using Kruskal's algorithm in a separate thread, to allow for step-by-step generation.
 * Use getMaze() to get a reference to the current KruskalMaze object. */
public class KruskalThread extends MazeThread {

    public KruskalThread(int size, Object lock) {
        maze = new KruskalMaze(size, lock);
    }
}

