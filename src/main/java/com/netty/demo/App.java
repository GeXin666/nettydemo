package com.netty.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * SpringBoot与Netty集成
 */
@SpringBootApplication
@ServletComponentScan
public class App extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(App.class).
               /* web(WebApplicationType.NONE).*/run(args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
