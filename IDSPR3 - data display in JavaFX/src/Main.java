import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args){launch(args);}

    @Override public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        loader.setController(new Controller(new TemperatureGenerator()));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Temperature Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
