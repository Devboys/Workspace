package jsfaber.ec1opg1.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args); //calls start (as overriden further below)
    }

    @Override //Make sure we override start.
    public void start(Stage primaryStage) { //main entry point for javafx, is called by launch()
        primaryStage.setTitle("Hello World!"); //set the title of the stage aka the window
        Button btn = new Button(); //create a new button object

    }
}
