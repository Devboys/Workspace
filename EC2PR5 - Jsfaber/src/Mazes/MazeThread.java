package Mazes;

public abstract class MazeThread extends Thread {

    protected Maze maze;

    public Maze getMaze(){
        return maze;
    }

    @Override public void run(){
        try{
            maze.generate();
        }catch (InterruptedException e) {
            System.out.println("Successfully closed current thread");
        }
    }
}
