/*
* Essential Computing 1 Assignment #5 - "Handle events on multiple buttons"
*                                + #6 - "Run through the minesweeper objects to find neighbouring objects"
* Jeppe S. Faber 59774
* ----------------------------------------------------------------------------------------------------------------------
* Class: Main.java
*   Main.java contains the main method and very simply initiates the primary window(stage) and plants a mainMenu item
*   inside it.
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
        primaryStage.setTitle("MinesweeperV3");
        primaryStage.setScene(new Scene(menuRoot, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
