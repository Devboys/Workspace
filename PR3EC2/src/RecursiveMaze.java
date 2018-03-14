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
    private static final int NUMDIRECTIONS = 4;
    private static final int[] STARTPOS = {0,0};

    public RecursiveMaze(int size){
        width = size;
        height = size;
        maze = new Cell[width][height];

        rnd = new Random();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                maze[i][j] = new Cell();
            }
        }

        recursiveCall(STARTPOS[0],STARTPOS[1]);
    }


    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getArray() {
        return maze;
    }

    private void recursiveCall(int x, int y){
        Cell currentCell = maze[x][y];
        currentCell.setVisited(true);

        ArrayList<Direction> availableDirections = getAvailableCells(x, y);
//        System.out.println("------------------");
//        for(int i = 0; i < availableDirections.size(); i++) {
//            if(availableDirections.get(i) == Direction.NORTH){
//                System.out.println("NORTH");
//            }
//            if(availableDirections.get(i) == Direction.SOUTH){
//                System.out.println("SOUTH");
//            }
//            if(availableDirections.get(i) == Direction.EAST){
//                System.out.println("EAST");
//            }
//            if(availableDirections.get(i) == Direction.WEST){
//                System.out.println("WEST");
//            }
//
//        }

        System.out.println(availableDirections.size());
        while(availableDirections.size() != 0) {
            availableDirections = getAvailableCells(x, y);

            Direction nextDirection = Direction.DEFAULT;
            if(availableDirections.size() != 0) {
                nextDirection = availableDirections.get(rnd.nextInt(availableDirections.size()));
            }

            switch (nextDirection) {
                case NORTH:
                    //System.out.println("GOING NORTH");
                    Cell northCell = maze[x][y-1];
                    availableDirections.remove(nextDirection);
                    currentCell.setNorth(true);
                    northCell.setSouth(true);
                    recursiveCall(x, y-1);
                    break;
                case SOUTH:
                    //System.out.println("GOING SOUTH");
                    Cell southCell = maze[x][y+1];
                    availableDirections.remove(nextDirection);

                    currentCell.setSouth(true);
                    southCell.setNorth(true);

                    recursiveCall(x, y+1);
                    break;
                case WEST:
                    //System.out.println("GOING WEST");
                    Cell westCell = maze[x-1][y];
                    availableDirections.remove(nextDirection);
                    currentCell.setWest(true);
                    westCell.setEast(true);
                    recursiveCall(x-1, y);
                    break;
                case EAST:
                    //System.out.println("GOING EAST");
                    Cell eastCell = maze[x+1][y];
                    availableDirections.remove(nextDirection);
                    currentCell.setEast(true);
                    eastCell.setWest(true);
                    recursiveCall(x+1, y);
                    break;
                case DEFAULT:
                    System.out.println("heyo");
            }
        }

    }

    private ArrayList<Direction> getAvailableCells(int x, int y){
        ArrayList<Direction> returnArray = new ArrayList<Direction>();

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
