/*
*  Essential Computing 1 Assignment #5 - "Handle events on multiple buttons"
*
*  Jeppe S. Faber 59774
*/
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(root, 600, 400);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(mainMenuScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}