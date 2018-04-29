package Mazes.Division;

import Mazes.Cell;

/** Special-case extension of cell for use in DivisionMaze. Simply starts with all walls being open. */
public class DivisionCell extends Cell{

    public DivisionCell(){
        north = south = east = west = true;
    }
}
