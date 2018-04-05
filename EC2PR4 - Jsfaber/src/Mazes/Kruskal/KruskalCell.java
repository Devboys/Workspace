package Mazes.Kruskal;

import Mazes.Cell;

/**
 * Extension of Mazes.Cell that adds one-way tree functionality for use in maze generation using Kruskal's algorithm.
 */
public class KruskalCell extends Cell {

    KruskalCell previous;

    public KruskalCell(){ super(); }

    /**
     * @return the root of whatever tree the cell is in
     */
    public KruskalCell getRoot(){
        if(previous == null){ return this; }
        else { return previous.getRoot(); }
    }

    public void setPrevious(KruskalCell prev){ previous = prev; }

}
