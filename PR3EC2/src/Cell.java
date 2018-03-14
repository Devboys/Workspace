import java.util.ArrayList;

public class Cell {
    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    private boolean visited;

    public Cell() {
        north = south = east = west = visited = false;
    }


    public boolean isNorth() { return north; }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public boolean isSouth() {
        return south;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public boolean isEast() {
        return east;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public boolean isWest() {
        return west;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }



}
