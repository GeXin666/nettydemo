package com.netty.demo;

import javafx.event.Event;

public class AppControler {

    public void onClick(Event event) {
        System.out.println("click" + event.getSource());
    }
}
