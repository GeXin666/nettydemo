package com.netty.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot与Netty集成
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE).run(args);
    }
}
