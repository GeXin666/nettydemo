package com.netty.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import org.springframework.core.io.ClassPathResource;


public class WindowApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        ClassPathResource resource = new ClassPathResource("Application.fxml");
//        Parent parent = FXMLLoader.load(resource.getURL());
//        Scene scene = new Scene(parent);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        Scene scene = new Scene(new Group());

        VBox root = new VBox();

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);
        webEngine.load("https://www.baidu.com");

        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}