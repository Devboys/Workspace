package assignment3.main;

public class Tile {

    private boolean isBomb;
    private boolean isPressed;
    private boolean isFlagged;
    private int xLoc;
    private int yLoc;

    public Tile(int xValue, int yValue, boolean bombStatus) {
        this.isBomb = bombStatus;
        this.xLoc = xValue;
        this.yLoc = yValue;
    }

    //getters
    public boolean getBombStatus()  {return isBomb;}
    public boolean hasBeenPressed() {return isPressed;}
    public boolean hasBeenFlagged() {return isFlagged;}
    public int getX() {return xLoc;}
    public int getY() {return yLoc;}

    //setters
    public void setPressed(boolean status)    {isPressed  = status;}
    public void setFlagged(boolean status)    {isFlagged = status;}
    public void setBombStatus(boolean status) {isBomb = status;}

}