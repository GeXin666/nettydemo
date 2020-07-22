package com.netty.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.springframework.core.io.ClassPathResource;


public class WindowApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
//        ClassPathResource resource = new ClassPathResource("Application.fxml");
//        Parent parent = FXMLLoader.load(resource.getURL());
//        Scene scene = new Scene(parent);
//        primaryStage.setScene(scene);
//        primaryStage.show();
        FlowPane root = new FlowPane();

        root.setHgap(10);
        root.setVgap(20);
        root.setPadding(new Insets(15,15,15,15));

        // Button 1
        Button button1= new Button("Button1");
        root.getChildren().add(button1);


        // Button 2
        Button button2 = new Button("Button2");
        button2.setPrefSize(100, 100);
        root.getChildren().add(button2);

        // TextField
        TextField textField = new TextField("Text Field");
        textField.setPrefWidth(110);


        root.getChildren().add(textField);

        // CheckBox
        CheckBox checkBox = new CheckBox("Check Box");

        root.getChildren().add(checkBox);

        // RadioButton
        RadioButton radioButton = new RadioButton("Radio Button");
        root.getChildren().add(radioButton);

        Scene scene = new Scene(root, 550, 250);

        primaryStage.setTitle("FlowPane Layout Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}