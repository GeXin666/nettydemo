package com.netty.demo.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField myText;

    @FXML
    public void onClick(ActionEvent event) {
        System.out.println(event.getTarget());
        myText.setText(event.getEventType().getName());

        try {
            URL url = getClass().getClassLoader().getResource("MainApp.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            AnchorPane personOverview = loader.load();
            Scene scene = new Scene(personOverview);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setOnHiding(e -> {
                System.out.println(e);
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
