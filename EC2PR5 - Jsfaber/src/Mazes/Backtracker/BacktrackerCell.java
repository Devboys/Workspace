package Mazes.Backtracker;

import Mazes.Cell;

public class BacktrackerCell extends Cell {

    private boolean visited;

    public BacktrackerCell(){
        super();
        visited = false;
    }

    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
}
