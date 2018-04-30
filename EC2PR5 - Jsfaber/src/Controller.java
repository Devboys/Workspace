import Mazes.Backtracker.BacktrackerMaze;
import Mazes.Backtracker.BacktrackerThread;
import Mazes.Division.DivisionMaze;
import Mazes.Division.DivisionThread;
import Mazes.Kruskal.KruskalMaze;
import Mazes.Kruskal.KruskalThread;
import Mazes.Maze;
import Mazes.MazeSolver;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Controller {

    @FXML Label modeLabel;
    @FXML Canvas canvas;

    private static final int mazeSize = 20;
    private static final int cellSize = 200/mazeSize;

    private Maze currMaze;
    private Thread currThread;

    private final Object stepLock = new Object();
    private boolean stepable;

    /**Creates, generates and draws an un-solved BacktrackerMaze. */
    public void newBacktrackerMaze() {
        stopCurrentThread();
        currThread = null;
        modeLabel.setText("Recursive Backtracker");

        if(stepable) {
            BacktrackerThread bThread = new BacktrackerThread(mazeSize, stepLock);
            currThread = bThread;
            bThread.start();
            currMaze = bThread.getMaze();
        }
        else{
            currMaze = new BacktrackerMaze(mazeSize);
        }

        drawMaze();
    }

    /**Creates, generates and draws an un-solved KruskalMaze. */
    public void newKruskalMaze(){
        stopCurrentThread();
        currThread = null;
        modeLabel.setText("Kruskal's Algorithm");

        if(stepable) {
            KruskalThread kThread = new KruskalThread(mazeSize, stepLock);
            kThread.start();
            currThread = kThread;
            currMaze = kThread.getMaze();
        }
        else{
            currMaze = new KruskalMaze(mazeSize);
        }
        drawMaze();
    }

    /**Creates, generates and draws an un-solved DivisionMaze. */
    public void newDivisionMaze(){
        stopCurrentThread();
        currThread = null;
        modeLabel.setText("Recursive Division");

        if(stepable) {
            DivisionThread dThread = new DivisionThread(mazeSize, stepLock);
            dThread.start();
            currThread = dThread;
            currMaze = dThread.getMaze();
        }
        else{
            currMaze = new DivisionMaze(mazeSize);
        }

        drawMaze();
    }

    /**Clears the screen and draws the walls of every cell in the maze, as well as indicators for start-cell, end-cell
     * and cells on solution-path. */
    private void drawMaze(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        //clear screen so that mazes aren't drawn on top of each other between calls.
        g.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        drawMockMaze();

        for (int x = 0; x < currMaze.getWidth(); x++) {
            for (int y = 0; y < currMaze.getHeight(); y++) {
                //draw start point
                if(currMaze.get(x, y).isStart()){
                    g.setFill(Color.GREEN);
                    g.fillRect(x * cellSize, (y*cellSize) ,
                            cellSize, cellSize);
                }
                //draw end point
                if(currMaze.get(x, y).isEnd()){
                    g.setFill(Color.RED);
                    g.fillRect(x * cellSize, (y*cellSize),
                            cellSize, cellSize);
                }

                //draw paths
                if(currMaze.get(x, y).isPath()){
                    int pathIndent = 4;
                    g.setFill(Color.rgb(150, 150, 150, 0.5));
                    g.fillRect(x*cellSize+pathIndent, y*cellSize + pathIndent,
                            cellSize-pathIndent*2, cellSize-pathIndent*2);
                }
                g.setFill(Color.BLACK);
                //Draw North walls
                if(!currMaze.get(x, y).isNorthOpen()){
                    g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                    g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                }

                //Draw South walls
                if(!currMaze.get(x, y).isSouthOpen()){
                    g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                    g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);

                }
                //Draw East walls
                if(!currMaze.get(x, y).isEastOpen()){
                    g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                    g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }
                //Draw West walls
                if(!currMaze.get(x, y).isWestOpen()){
                    g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                    g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                }
            }
        }
    }

    /**Draws an opaque outline of every cell in the current maze. Does not clear screen.*/
    private void drawMockMaze(){
        GraphicsContext g = canvas.getGraphicsContext2D();

        Paint oldColor = g.getStroke();
        g.setStroke(Color.rgb(50, 50, 50, 0.02));

        for (int x = 0; x < currMaze.getWidth(); x++) {
            for (int y = 0; y < currMaze.getHeight(); y++) {
                //Draw North walls
                g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                //Draw South walls
                g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                //Draw East walls
                g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                //Draw West walls
                g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
            }
        }
        g.setStroke(oldColor);
    }

    /**notifies the step-lock, allowing the current maze to take a single generation step.*/
    public void step(){
        if(currMaze!= null && stepable) {
            if(!currMaze.isFinished()) {
                synchronized (stepLock) {
                    stepLock.notify();
                }
            }
            drawMaze();
        }
    }

    /**Continuously notifies the step-lock until maze generation is finished.*/
    public void completeStep(){
        if(currMaze != null && stepable) {
            while(!currMaze.isFinished()) {
                step();
            }
            drawMaze();
        }
    }

    /**Solves the current maze*/
    public void solve(){
        if(currMaze != null && currMaze.isFinished()) {
            MazeSolver solver = new MazeSolver(currMaze);
            solver.solve();
            drawMaze();
        }
    }

    /**Interrupts the current mazeThread.*/
    public void stopCurrentThread(){
        if(currThread != null) {
            currThread.interrupt();
        }
    }

    public void switchStepable(){ stepable = !stepable; }

}
