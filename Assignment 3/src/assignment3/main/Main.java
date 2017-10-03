package assignment3.main;

public class Main {
    public static void main(String[] args) {

        int fieldSize = 10 ;
        int numBombs = 50;

        Field theField = new Field(fieldSize, fieldSize, numBombs);
        theField.printFieldToConsole();
    }
}
