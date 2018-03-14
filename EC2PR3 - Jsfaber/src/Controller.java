import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    Button drawButton;
    @FXML
    Canvas canvas;

    RecursiveMaze rMaze;

    private static final int mazeSize = 20;
    private static final int cellSize = 10;

    /**
     * Generates a new maze and draws it to the canvas.
     */
    public void newMaze() {
        rMaze = new RecursiveMaze(mazeSize);
        GraphicsContext g = canvas.getGraphicsContext2D();

        //clear screen so that mazes aren't drawn on top of each other between calls.
        g.clearRect(0,0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        for (int x = 0; x < rMaze.getWidth(); x++) {
            for (int y = 0; y < rMaze.getHeight(); y++) {

                //Draw north walls
                if(!rMaze.get(x, y).isNorth()){
                    g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                }

                //Draw South walls
                if(!rMaze.get(x, y).isSouth()){
                    g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //Draw East walls
                if(!rMaze.get(x, y).isEast()){
                    g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //Draw West walls
                if(!rMaze.get(x, y).isWest()){
                    g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                }
            }
        }
    }
}
