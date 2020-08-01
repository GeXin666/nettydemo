package com.netty.demo.fx;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FirstFxApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        Text text = new Text("This is a longer JavaFX text.");

        text.setFontSmoothingType(FontSmoothingType.GRAY);

        //text.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        Scene scene = new Scene(new VBox(text), 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
