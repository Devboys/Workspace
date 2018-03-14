package com.jsfaber.ec2.mazegen.recursive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RecursiveMaze {
    private int width;
    private int height;

    private Cell[][] mazeGrid;
    private Random rnd;

    private int[] startPosition;

    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public Cell getCell(int x, int y) { return mazeGrid[y][x]; }


    //default constructor, setting initial position to (0,0)
    public RecursiveMaze(int width, int height){
        this(width, height, new int[] {0,0});
    }

    public RecursiveMaze(int width, int height, int[]startPos){
        this.startPosition = startPos;
        this.width = width;
        this.height = height;
        rnd = new Random();
        mazeGrid = new Cell[height][width];

        //fill mazeGrid with default tiles(all sides closed).
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                mazeGrid[i][j] = new Cell();
            }
        }
        //generate the maze from given start position.
        clearPath(startPosition[0], startPosition[1]);
    }

    public Cell[][] getGrid(){return mazeGrid;}

    private void clearPath(int yCoord, int xCoord){
        Cell currentTile = mazeGrid[yCoord][xCoord];
        Cell nextTile;

        currentTile.setVisited(true);


        int[] validNeighbours = getAvailablePaths(yCoord, xCoord);

        if(validNeighbours.length != 0){

            int pathChoice = validNeighbours[rnd.nextInt(validNeighbours.length)];

            switch (pathChoice) {
                case 0: //NORTH
                    currentTile.setNorth(true);
                    nextTile = mazeGrid[yCoord-1][xCoord];
                    nextTile.setSouth(true);
                    clearPath(yCoord-1, xCoord);
                    break;
                case 1: //SOUTH
                    currentTile.setSouth(true);
                    nextTile = mazeGrid[yCoord+1][xCoord];
                    nextTile.setNorth(true);
                    clearPath(yCoord+1, xCoord);
                    break;
                case 2: //EAST
                    currentTile.setEast(true);
                    nextTile = mazeGrid[yCoord][xCoord+1];
                    nextTile.setWest(true);
                    clearPath(yCoord, xCoord+1);
                    break;
                case 3: //WEST
                    currentTile.setWest(true);
                    nextTile = mazeGrid[yCoord][xCoord-1];
                    nextTile.setEast(true);
                    clearPath(yCoord, xCoord-1);
                    break;
            }
        }
    }

    private int[] getAvailablePaths(int yCoord, int xCoord){
        ArrayList<Integer> validDirections = new ArrayList<>();
        int size = 0;

        //add north
        try {
            if (!mazeGrid[yCoord - 1][xCoord].isVisited()) {
                validDirections.add(0);
                size++;
            }
        }catch (IndexOutOfBoundsException e){}

        //add south
        try {
            if (!mazeGrid[yCoord + 1][xCoord].isVisited()) {
                validDirections.add(1);
                size++;
            }
        }catch (IndexOutOfBoundsException e){}

        //add east
        try {
            if (!mazeGrid[yCoord][xCoord + 1].isVisited()) {
                validDirections.add(2);
                size++;
            }
        }catch (IndexOutOfBoundsException e){}

        //add west
        try {
            if (!mazeGrid[yCoord][xCoord - 1].isVisited()) {
                validDirections.add(3);
                size++;
            }
        }catch (IndexOutOfBoundsException e){}

        int[] returnArray = new int[size];
        for(int i = 0; i < size; i++){
            returnArray[i] = validDirections.get(i);
        }

        return returnArray;
    }
}
