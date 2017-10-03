package assignment3.main;


public class Main{

    public static void main(String[] args){

        int fieldSize = 25 ;
        int numBombs = 5;
        Field theField = new Field(fieldSize, fieldSize, numBombs);
        theField.printFieldToConsole();



    }
}
