package com.netty.demo.fx;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {

    public MainAppController() {
        System.out.println("create MainAppController : " + hashCode());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize:" + location.toString());
    }
}
