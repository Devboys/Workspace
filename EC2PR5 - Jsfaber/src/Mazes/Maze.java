package Mazes;

import java.util.Random;

public abstract class Maze {

    protected Cell startCell;
    protected Cell endCell;

    protected int height;
    protected int width;

    protected Cell[][] maze;

    protected Random rnd;

    protected boolean finished;

    protected enum Edges {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public Maze(int size){
        height = size;
        width = size;
        rnd = new Random();
        maze = new Cell[width][height];
    }
    /**@param x The x-coordinate of the element.
     * @param y The y-coordinate of the element.
     * @return The Cell-element at the given position in the maze.*/
    public Cell get(int x, int y) {return maze[x][y]; }
    /**Picks a random element on one of the outer walls of the maze and removes its outermost wall.
     * @return the randomly selected element.
     */
    protected Cell assignRandomExitCell(){
        int exitX;
        int exitY;
        Cell exitCell = null;
        boolean duplicate = true;

        while(duplicate) {
            Edges edge = Edges.values()[rnd.nextInt(Edges.values().length)];

            switch (edge) {
                case NORTH:
                    exitX = rnd.nextInt(width);
                    exitY = 0;
                    exitCell = maze[exitX][exitY];
                    if(startCell == null || exitCell != startCell) {
                        exitCell.setNorthOpen(true);
                        duplicate = false;
                    }
                    break;
                case SOUTH:
                    exitX = rnd.nextInt(width);
                    exitY = height - 1;
                    exitCell = maze[exitX][exitY];
                    if(startCell == null || exitCell != startCell) {
                        exitCell.setSouthOpen(true);
                        duplicate = false;
                    }
                    break;
                case EAST:
                    exitX = width - 1;
                    exitY = rnd.nextInt(height);
                    exitCell = maze[exitX][exitY];
                    if(startCell == null || exitCell != startCell) {
                        exitCell.setEastOpen(true);
                        duplicate = false;
                    }
                    break;
                case WEST:
                    exitX = 0;
                    exitY = rnd.nextInt(height);
                    exitCell = maze[exitX][exitY];
                    if(startCell == null || exitCell != startCell) {
                        exitCell.setWestOpen(true);
                        duplicate = false;
                    }
                    break;
            }
        }
        return exitCell;
    }
    /** @return the number of elements in the x-direction of the maze. */
    public int getWidth(){ return width; }
    /** @return the number of elements in the y-direction of the maze */
    public int getHeight(){ return height; }

    public boolean isFinished() {
        return finished;
    }

    public abstract void generate() throws InterruptedException;
}
