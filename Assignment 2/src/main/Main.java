/*
*  Essential Computing 1 Assignment 2 - "Arrays: (Very) Simplified Minesweeper game"
*  Jeppe S. Faber 59774
*
*  Essentially a simple guessing game, where a user is prompted to enter a value between 0 and some set value, if the
*  users guess lands on a 'bomb' tile, the game ends and the program exits.
*  bombCount is used to set the number of bombs placed randomly throughout the minefield.
*/
package main;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int mineFieldSize = 10; // used to set the number of tiles available for guessing.
    private static int numBombs = 3;       // used to set the amount of bombs placed randomly throughout the minefield.

    /*creates a minefield of a given size and places a given number of bombs at random positions in it.
      minefield is a boolean array where: bomb = true, nothing = false. */
    private static Boolean[] generate1DMinefield(int size, int bombCount) {
        Boolean[] mineField = new Boolean[size];

        for(int i = 0; i < size; i++) {         //fill minefield with booleans to initialize values.
            mineField[i] = false;
        }

        Random randGenerator = new Random();
        for(int i = 0; i < bombCount; i++ ) {   //put minefield bombs at random locations.
            int bombLocation = randGenerator.nextInt(size);
            mineField[bombLocation] = true;
        }
        return mineField;
    }

    public static void main(String[] args) {
        Boolean[] mineField = generate1DMinefield(mineFieldSize, numBombs);

        Scanner userInputScanner = new Scanner(System.in);
        boolean gameOver = false;
        while(!gameOver) {
            System.out.println("Guess a number between 0 and " + (mineFieldSize - 1));
            int userGuess = userInputScanner.nextInt();

            if(userGuess < 0 || userGuess > 9) {  //make sure the user doesn't enter a value out of bounds.
                System.out.println("Oops, looks like you entered a value outside of 0-9, try again...");
            }
            else if(mineField[userGuess]) {       //if user hits a bomb, its game over.
                System.out.println("\n BOOM!\n  Game Over...");
                gameOver = true;
            }
            else{                                 //if user hits nothing, continue.
                System.out.println("\n *fizzle* \n------------------------------");
            }
        }
    }
}
