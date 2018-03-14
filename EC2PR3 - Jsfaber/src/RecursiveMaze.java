import java.util.ArrayList;
import java.util.Random;

public class RecursiveMaze {

    Cell[][] maze;
    private int width;
    private int height;
    Random rnd;

    private enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        DEFAULT
    }

    public RecursiveMaze(int size){
        this(size, 0, 0);
    }

    public RecursiveMaze(int size, int startX, int startY){
        width = size;
        height = size;
        maze = new Cell[width][height];
        rnd = new Random();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = new Cell();
            }
        }
        digTile(startY,startY);
    }

    /**
     * @return how many elements are in the x-direction of the maze.
     */
    public int getWidth(){
        return width;
    }

    /**
     * @return how many elements are in the y-direction of the maze
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param x The x-coordinate of the element.
     * @param y The y-coordinate of the element.
     * @return The Cell-element at the given position in the maze.
     */
    public Cell get(int x, int y){
        return maze[x][y];
    }

    //Recursive-backtracker algorithm.
    private void digTile(int x, int y){
        Cell currentCell = maze[x][y];
        currentCell.setVisited(true);

        ArrayList<Direction> availableDirections;

        boolean neighboursAvailable = true;
        while(neighboursAvailable) {

            //availableDirections holds all available directions from the cell at the given position.
            availableDirections = getAvailableCells(x, y);

            Direction nextDirection = Direction.DEFAULT;
            if(availableDirections.size() != 0) {
                nextDirection = availableDirections.get(rnd.nextInt(availableDirections.size()));
            }

            switch (nextDirection) {
                case NORTH:
                    Cell northCell = maze[x][y-1];
                    availableDirections.remove(nextDirection);
                    currentCell.setNorth(true);
                    northCell.setSouth(true);
                    digTile(x, y-1);
                    break;
                case SOUTH:
                    Cell southCell = maze[x][y+1];
                    availableDirections.remove(nextDirection);
                    currentCell.setSouth(true);
                    southCell.setNorth(true);
                    digTile(x, y+1);
                    break;
                case WEST:
                    Cell westCell = maze[x-1][y];
                    availableDirections.remove(nextDirection);
                    currentCell.setWest(true);
                    westCell.setEast(true);
                    digTile(x-1, y);
                    break;
                case EAST:
                    Cell eastCell = maze[x+1][y];
                    availableDirections.remove(nextDirection);
                    currentCell.setEast(true);
                    eastCell.setWest(true);
                    digTile(x+1, y);
                    break;
                case DEFAULT:
                    //if nextDirection is default, it must mean that no valid directions are available.
                    neighboursAvailable = false;
                    break;
            }
        }
    }

    //Returns an ArrayList of Directions to all neighbouring non-visited cells.
    private ArrayList<Direction> getAvailableCells(int x, int y){
        ArrayList<Direction> returnArray = new ArrayList<Direction>(4);

        //WEST
        try {
            if (!maze[x - 1][y].isVisited()) {
                returnArray.add(Direction.WEST);
            }
        }catch (IndexOutOfBoundsException e){}

        //EAST
        try {
            if (!maze[x + 1][y].isVisited()) {
                returnArray.add(Direction.EAST);
            }
        }catch(IndexOutOfBoundsException e){
        }

        //NORTH
        try {
            if (!maze[x][y - 1].isVisited()) {
                returnArray.add(Direction.NORTH);
            }
        }catch(IndexOutOfBoundsException e){}

        //SOUTH
        try {
            if (!maze[x][y + 1].isVisited()) {
                returnArray.add(Direction.SOUTH);
            }
        }catch(IndexOutOfBoundsException e){}
        return returnArray;
    }

}
