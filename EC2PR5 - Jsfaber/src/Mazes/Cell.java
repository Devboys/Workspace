package Mazes;

public class Cell {
    protected boolean north;
    protected boolean south;
    protected boolean east;
    protected boolean west;

    protected boolean start;
    protected boolean end;


    public Cell() {
        north = south = east = west = false;
    }

    public boolean isNorthOpen(){ return north; }
    public boolean isSouthOpen(){ return south; }
    public boolean isEastOpen(){ return east; }
    public boolean isWestOpen(){ return west; }
    public boolean isStart(){ return start; }
    public boolean isEnd(){ return end; }

    public void setNorthOpen(boolean north){ this.north = north; }
    public void setSouthOpen(boolean south){ this.south = south; }
    public void setEastOpen(boolean east){ this.east = east; }
    public void setWestOpen(boolean west){ this.west = west; }
    public void setStart(boolean start){this.start = start; }
    public void setEnd(boolean end){this.end = end; }

}
