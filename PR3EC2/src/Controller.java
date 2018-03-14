import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    Button drawButton;

    @FXML
    Canvas canvas;

    Cell[][] grid;
    RecursiveMaze rMaze;

    private static final int cellSize = 20;

    public void drawMaze() {
        rMaze = new RecursiveMaze(10);
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0,0, 600, 500);
        grid = rMaze.getArray();

        for (int x = 0; x < rMaze.getWidth(); x++) {
            for (int y = 0; y < rMaze.getHeight(); y++) {

                //NORTH
                if(!grid[x][y].isNorth()){
                    g.strokeLine(x*cellSize, y*cellSize, (x+1)*cellSize, y*cellSize);
                }

                //SOUTH
                if(!grid[x][y].isSouth()){
                    g.strokeLine(x*cellSize, (y+1)*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //EAST
                if(!grid[x][y].isEast()){
                    g.strokeLine((x+1)*cellSize, y*cellSize, (x+1)*cellSize, (y+1)*cellSize);
                }

                //WEST
                if(!grid[x][y].isWest()){
                    g.strokeLine(x*cellSize, y*cellSize, x*cellSize, (y+1)*cellSize);
                }
            }
        }
    }

}
