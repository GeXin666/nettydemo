package com.netty.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Config {

    public static int serverPort;

    @Value("${netty.server.port}")
    public void setStationPort(int stationPort) {
        Config.serverPort = stationPort;
    }

    @Bean
    public TaskExecutor configTaskExecutor() {
        TaskExecutorBuilder builder = new TaskExecutorBuilder()
                .corePoolSize(30)
                .queueCapacity(1024)
                .threadNamePrefix("mytask_thread");
        return builder.build();
    }

    @Bean
    public ThreadPoolExecutor configThreadPoolExecutor() {
        log.info("init ThreadPoolExecutor");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024));
        return threadPoolExecutor;
    }
}
