package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public Label helloWorld;
    public TextField txtfield;

    public void buttonPrint() {
        System.out.println("button has been pressed in the console");
    }

    public void changeText() {
        helloWorld.setText(txtfield.getText());
    }

}
