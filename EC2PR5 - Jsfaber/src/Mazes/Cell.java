package Mazes;


/**Simple data-structure that holds all values relevant to the generic cells used in mazes. Serves as a supertype
 * for all special-case cells used in maze generation, but all post-generation functionality should only use generic
 * cells.*/
public class Cell {
    protected boolean north;
    protected boolean south;
    protected boolean east;
    protected boolean west;

    private boolean start;
    private boolean end;

    private boolean visited;
    private boolean path;

    public boolean isNorthOpen(){ return north; }
    public boolean isSouthOpen(){ return south; }
    public boolean isEastOpen(){ return east; }
    public boolean isWestOpen(){ return west; }
    public boolean isStart(){ return start; }
    public boolean isEnd(){ return end; }
    public boolean isNotVisited() { return !visited; }
    public boolean isPath() { return path; }

    public void setNorthOpen(boolean north){ this.north = north; }
    public void setSouthOpen(boolean south){ this.south = south; }
    public void setEastOpen(boolean east){ this.east = east; }
    public void setWestOpen(boolean west){ this.west = west; }
    public void setStart(boolean start){this.start = start; }
    public void setEnd(boolean end){this.end = end; }
    public void setVisited(boolean visited) { this.visited = visited; }
    public void setPath( boolean path) {this.path = path; }

}
