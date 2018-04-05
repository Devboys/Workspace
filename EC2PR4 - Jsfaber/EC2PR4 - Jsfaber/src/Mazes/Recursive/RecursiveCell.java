package Mazes.Recursive;

import Mazes.Cell;

public class RecursiveCell extends Cell {

    private boolean visited;

    public RecursiveCell(){
        super();
        visited = false;
    }

    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
}
