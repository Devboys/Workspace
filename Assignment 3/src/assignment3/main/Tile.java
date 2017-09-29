package assignment3.main;

public class Tile {

    private boolean isBomb;
    private int xLoc;
    private int yLoc;

    //constructor
    public Tile(int xLoc, int yLoc, boolean isBomb) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.isBomb = isBomb;
    }

    //getter methods
    public boolean isBomb(){ return isBomb; }

    //setter methods
    public void setBomb(boolean value) { isBomb = value; }
}
