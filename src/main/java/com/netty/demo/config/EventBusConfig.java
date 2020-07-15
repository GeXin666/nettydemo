package com.netty.demo.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.netty.demo.events.EventSubscribe;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class EventBusConfig {

    @Bean
    public EventBus registerEventBus() {
        AsyncEventBus eventBus = new AsyncEventBus("eventBusName",Executors.newFixedThreadPool(50));
        eventBus.register(eventSubscribe());
        return eventBus;
    }

    @Bean
    public EventSubscribe eventSubscribe() {
        return new EventSubscribe();
    }
}
