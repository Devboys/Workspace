import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 500;

    private Controller appController;

    @Override public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mazeFXML.fxml"));

        Parent root = loader.load();
        appController = loader.getController();

        primaryStage.setTitle("Recursive Maze Generator");
        primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

    @Override public void stop(){
        System.out.println("Closing application...");
        appController.stopCurrentThread();
    }
}
