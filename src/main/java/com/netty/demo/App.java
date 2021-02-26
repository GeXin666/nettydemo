package com.netty.demo;

import com.netty.demo.vertx.RedisVerticle;
import io.vertx.core.Vertx;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot与Netty集成
 */
@SpringBootApplication
public class App {

    //public static final Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        //new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE).run(args);
        new SpringApplicationBuilder(App.class).run(args);
        //vertx.deployVerticle(new RedisVerticle());
    }
}
