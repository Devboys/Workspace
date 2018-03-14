package com.jsfaber.ec2.mazeGUI;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import com.jsfaber.ec2.mazegen.recursive.*;
import javafx.scene.paint.Color;

public class MazeGenController {

    @FXML
    Button generateBtn;

    @FXML
    Canvas canvas;

    private static final int cellSize = 10;
    private static final int wedge = 2;


    public void generateMaze(){
        RecursiveMaze maze = new RecursiveMaze(10, 10);
//        for(int i = 0; i < maze.getHeight(); i++){
//            for(int j = 0; j < maze.getWidth(); j++){
//                Cell currCell = maze.getCell(j, i);
//                if(!currCell.northOpen()){
//                    if(!currCell.southOpen()){
//                        if(!currCell.eastOpen()){
//                            if(!currCell.westOpen()){
//                                System.out.println("REEEEE");}
//                        }
//                    }
//                }
//            }
//        }

        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.BLACK);
        int drawW = maze.getWidth()*cellSize + maze.getWidth() * wedge;
        int drawH = maze.getHeight()*cellSize + maze.getHeight() * wedge;

        g.fillRect(0,0, drawW, drawH);
        g.setFill(Color.WHITE);
        g.setStroke(Color.WHITE);

        for(int i = 0; i < maze.getHeight(); i++){
            for(int j = 0; j < maze.getWidth(); j++ ){
                g.fillRect(j * cellSize, i*cellSize, cellSize, cellSize);
                Cell currCell = maze.getCell(j, i);


                if(currCell.northOpen()){
                    int lineSX = j * cellSize;
                    int lineSY = i * cellSize;
                    int lineEX = lineSX + cellSize;
                    int lineEY = lineSY;
                    g.strokeLine(lineSX, lineSY, lineEX, lineEY);
                }

                if(currCell.southOpen()){
                    int lineSX = j * cellSize;
                    int lineSY = (i+1) * cellSize;
                    int lineEX = lineSX + cellSize;
                    int lineEY = lineSY;
                    g.strokeLine(lineSX, lineSY, lineEX, lineEY);
                }

                if(currCell.eastOpen()){
                    int lineSX = (j+1) * cellSize;
                    int lineSY = i * cellSize;
                    int lineEX = lineSX;
                    int lineEY = lineSY + cellSize;
                    g.strokeLine(lineSX, lineSY, lineEX, lineEY);
                }
                if(currCell.westOpen()){
                    int lineSX = j * cellSize;
                    int lineSY = i * cellSize;
                    int lineEX = lineSX;
                    int lineEY = lineSY + cellSize;
                    g.strokeLine(lineSX, lineSY, lineEX, lineEY);
                }

            }
        }

        System.out.println("generated");
    }

//        for (int i = 0; i < maze.getSize(); i++) {
//            for (int j = 0; j < maze.getSize(); j++) {
//                if (maze.getCell(i,j).north == true) graphicsContext.strokeLine(10*i,10*j,10*i+10,10*j);
//                if (maze.getCell(i,j).west == true) graphicsContext.strokeLine(10*i,10*j,10*i,10*j+10);
}
