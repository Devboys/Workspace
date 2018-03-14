import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MazeGrid mazeRoot = new MazeGrid();
        primaryStage.setTitle("Great Big Maze");
        primaryStage.setScene(new Scene(mazeRoot, 600, 600));
        primaryStage.show();
    }
}
