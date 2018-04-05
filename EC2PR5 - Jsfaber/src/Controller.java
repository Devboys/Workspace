import Mazes.Division.DivisionMaze;
import Mazes.Kruskal.KruskalMaze;
import Mazes.Backtracker.BacktrackerMaze;
import Mazes.Maze;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    Label modeLabel;
    @FXML
    Canvas canvas;

    private static final int mazeSize = 20;
    private static final int cellSize = 10;

    private Maze currMaze;

    /**
     * Generates a new maze and draws it to the canvas.
     */
    public void newBacktrackerMaze() {
        modeLabel.setText("Recursive Backtracker");
        BacktrackerMaze bMaze = new BacktrackerMaze(mazeSize);
        currMaze = bMaze;
        drawMaze();
    }

    public void newKruskalMaze(){
        modeLabel.setText("Kruskal's Algorithm");
        KruskalMaze kMaze = new KruskalMaze(mazeSize);
        currMaze = kMaze;
        drawMaze();
    }

    public void newDivisionMaze(){
        modeLabel.setText("Recursive Division");
        DivisionMaze dMaze = new DivisionMaze(mazeSize);
        currMaze = dMaze;
        drawMaze();
    }

    private void drawMaze(){
        GraphicsContext g = canvas.getGraphicsContext2D();

        //clear screen so that mazes aren't drawn on top of each other between calls.
        g.clearRect(0,0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        int lineWidth = 2;
        g.setLineWidth(lineWidth);

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

                //Draw north walls
                if(!currMaze.get(x, y).isNorthOpen()){
                    g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                }

                //Draw South walls
                if(!currMaze.get(x, y).isSouthOpen()){
                    g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //Draw East walls
                if(!currMaze.get(x, y).isEastOpen()){
                    g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //Draw West walls
                if(!currMaze.get(x, y).isWestOpen()){
                    g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                }




            }
        }
    }

    public void solve(){
        if(currMaze != null) {
            solveMaze(currMaze);
            drawMaze();
        }
    }

    private void solveMaze(Maze maze){
        System.out.println("BEEP BOOP SOLVE");
    }
}
