/*
*  Essential Computing 1 Assignment 1 - "JavaFX: GUI and events
*  Jeppe S. Faber 59774
*/
package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public Label buttonLabel;     //declare the label for the text button in sample.fxml
    public TextField textField;   //declare the textfield from sample.fxml

    private int clickCount = 0; //keep track of the number of button clicks


    public void buttonPrint() {

        clickCount++;                         //when pressed, increment the number of clicks
        if(clickCount == 1) {                 //if this is the first click, write one thing
            System.out.println("button has been pressed in the console");
        }
        else if(clickCount == 2) {            //if it is the second click, write another thing
            System.out.println("Second time");
            clickCount = 0;                   //we don't want to register more than two clicks, so reset counter
        }
    }

    public void changeText() {
        if(textField.getLength() != 0) {                            //make sure the textfield is not empty
            buttonLabel.setText("Player1: " + textField.getText()); //change the label text to match the textfield text
            textField.clear();                                      //now clear the textfield of any text
        }
    }

}