package Mazes;

public class Cell {
    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    public Cell() {
        north = south = east = west = false;
    }

    public boolean isNorth() { return north; }
    public boolean isSouth() { return south; }
    public boolean isEast() { return east; }
    public boolean isWest() { return west; }

    public void setNorth(boolean north) { this.north = north; }
    public void setSouth(boolean south) { this.south = south; }
    public void setEast(boolean east) { this.east = east; }
    public void setWest(boolean west) { this.west = west; }

}
