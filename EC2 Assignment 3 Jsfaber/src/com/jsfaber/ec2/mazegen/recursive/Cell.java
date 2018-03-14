package com.jsfaber.ec2.mazegen.recursive;

public class Cell {

    //which directions available for traversal from the tile.
    private boolean north;
    private boolean south;
    private boolean east;
    private boolean west;

    //whether or not the tile has already been used for maze-generation.
    private boolean visited;

    Cell(){
        north = south = east = west = false;
        visited = false;
    }

    public boolean northOpen() { return north; }
    public boolean southOpen() { return south; }
    public boolean eastOpen()  { return east; }
    public boolean westOpen()  { return west; }
    public boolean isVisited() { return visited; }

    //package-scope setters
    void setSouth(boolean south)     { this.south = south; }
    void setEast(boolean east)       { this.east = east; }
    void setWest(boolean west)       { this.west = west; }
    void setNorth(boolean north)     { this.north = north; }
    void setVisited(boolean visited) { this.visited = visited; }
}
