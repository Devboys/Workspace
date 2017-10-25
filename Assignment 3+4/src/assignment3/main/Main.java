/*
*  Essential Computing 1 Assignment #3 - "OOP: Design a class for a minesweeper field"
*                                 + #4 - "2D array: Create Minesweeper-fields"
*  Jeppe S. Faber 59774
*/
package assignment3.main;

public class Main{

    public static void main(String[] args){

        int fieldSize = 10;
        int numBombs = 5;

        Field theField = new Field(fieldSize, fieldSize, numBombs);
        theField.printToConsole();
    }
}
