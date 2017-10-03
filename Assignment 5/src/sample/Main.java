package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception{
        //first set up the field.
        int fieldSize = 25 ;
        int numBombs = 5;
        Field theField = new Field(fieldSize, fieldSize, numBombs);

        //now create the loader
        FXMLLoader theLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

        //add theField as a controller, to allow us to use its methods in our GUI.
        theLoader.setController(theField);

        //and setup the scene
        AnchorPane thePane = theLoader.load();
        Scene theScene = new Scene(thePane, 300, 275);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(theScene);
        primaryStage.show();
    }
}
