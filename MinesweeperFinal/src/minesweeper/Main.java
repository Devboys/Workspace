/*
* Essential Computing Mini Project

* Jeppe S. Faber 59774
* ----------------------------------------------------------------------------------------------------------------------
* Class: Main.java
*   Main contains the main method and very simply initiates the primary window(stage) and plants a mainMenu scene
*   inside it. The mainMenu scene is set-up using mainMenu.fxml and is loaded into the stage through JavaFXML.
*/
package minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent menuRoot = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(new Scene(menuRoot, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
