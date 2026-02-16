package com.chessgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChessApp extends Application{
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Welcome to Chess!");
        Scene scene = new Scene(label, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
