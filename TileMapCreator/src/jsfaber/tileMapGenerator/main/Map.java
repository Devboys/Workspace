package jsfaber.tileMapGenerator.main;

public class Map {
    
    private int[][] tileMap;
    
    public Map(int numTilesWidth, int numTilesHeight) {
        
        tileMap = new int[numTilesHeight][numTilesWidth];
        for(int i = 0; i < numTilesHeight; i++) {
            for(int j = 0; j < numTilesWidth; j++) {
                tileMap[i][j] = 0;
            }
        }
    }
    
    public void changeTile(int x, int y, int type) {
        //change tile [x][y] to type
        if(type > -1 && type < 7) {
            tileMap[y][x] = type;
        }
        
    }
    
    public void resetTile(int x, int y) {
        //resets tile [x][y] to 0
        tileMap[x][y] = 0;
        
    }
    
    public int getTile(int x, int y) {
        int temp = tileMap[x][y];
        return temp;
    }
    
    public int getNumTilesHeight() {
        return tileMap.length;
    }
    
    public int getNumTilesWidth() {
        return tileMap[0].length;
    }
    
    @Override
    public String toString() {
        String temp = "";
        
        for(int i = 0; i < tileMap.length; i++) {
            if(i != 0) { temp += "<>"; }
            for(int j = 0; j < tileMap[i].length; j++) {
                temp += tileMap[i][j] + "/";
            }
        }
        
        return temp;
    }
    
}
