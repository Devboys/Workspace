package jsfaber.platformer1.tileMap;

public class Tile {
    
//    public static final boolean REGULAR = ;
//    public static final int BLOCKED = 1;
    
    private boolean blocked;
    private int textureType;
    private int tileSize;
    
    //tilemap coordinates
    private int xIndex;
    private int yIndex;
    
    public Tile(int type, int tileSize, int xIndex, int yIndex) {
        
        textureType = type;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.tileSize = tileSize;
        
        if(type == 0) {
            blocked = false;
        }
        else {
            blocked = true;
        }
    }
    
    public int getXIndex() {
        return xIndex;
    }
    
    public int getYIndex() {
        return yIndex;
    }
    
    public boolean isBlocked() {
        return blocked;
    }
    
    public int getTextureType() {
        return textureType;
    }
    

    public int getTileUpperBound() {
        return getYIndex() * tileSize;
    }
    
    public int getTileLowerBound() {
        return (getYIndex() + 1) * tileSize;
    }
    
    public int getTileLeftBound() {
        return getXIndex() * tileSize;
    }
    
    public int getTileRightBound() {
        return (getXIndex() + 1) * tileSize; 
    }
}
