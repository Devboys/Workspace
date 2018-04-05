import Mazes.Kruskal.KruskalMaze;
import Mazes.Maze;
import Mazes.Recursive.RecursiveMaze;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    Button recursiveButton;
    @FXML
    Button kruskalButton;
    @FXML
    Label modeLabel;
    @FXML
    Canvas canvas;

    private static final int mazeSize = 20;
    private static final int cellSize = 10;

    /**
     * Generates a new maze and draws it to the canvas.
     */
    public void newRecursiveMaze() {
        modeLabel.setText("Recursive Back-tracker");
        RecursiveMaze rMaze = new RecursiveMaze(mazeSize);
        drawMaze(rMaze);
    }

    public void newKruskalMaze(){
        modeLabel.setText("Kruskal's Algorithm");
        KruskalMaze kMaze = new KruskalMaze(mazeSize);
        drawMaze(kMaze);
    }

    private void drawMaze(Maze maze){
        GraphicsContext g = canvas.getGraphicsContext2D();

        //clear screen so that mazes aren't drawn on top of each other between calls.
        g.clearRect(0,0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {

                //Draw north walls
                if(!maze.get(x, y).isNorth()){
                    g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                }

                //Draw South walls
                if(!maze.get(x, y).isSouth()){
                    g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //Draw East walls
                if(!maze.get(x, y).isEast()){
                    g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //Draw West walls
                if(!maze.get(x, y).isWest()){
                    g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                }
            }
        }
    }
}
