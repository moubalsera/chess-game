package com.chessgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ChessApp extends Application{
    @Override
    public void start(Stage primaryStage) {
        
        GridPane grid = new GridPane();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(80, 80);
                if ((row + col) % 2 == 0) {
                    cell.setStyle("-fx-background-color: white;");
                } else {
                    cell.setStyle("-fx-background-color: gray;");
                }
                grid.add(cell, col, row);
            }
        }

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mou's Chess Game");
        primaryStage.show();
       
    }

    public static void main(String[] args) {
        launch(args);
    }
}
