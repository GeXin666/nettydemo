package com.netty.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class RedisVerticle extends AbstractVerticle {

    public static volatile RedisAPI redisAPI;

    private  RedisOptions redisOptions;

    @Override
    public void start() {
        redisOptions = new RedisOptions()
                .setConnectionString("redis://192.168.80.112:6379")
                .setMaxPoolSize(8);
                //.setMaxWaitingHandlers(32);
        createRedisClient(redisOptions);
    }

    private void createRedisClient(RedisOptions redisOptions) {
        Redis.createClient(vertx, redisOptions)
                .connect()
                .onSuccess(conn -> {
                    redisAPI = RedisAPI.api(conn);

                    vertx.setPeriodic(5 * 1000, handler -> {
                        redisAPI.ping(new ArrayList<>(), result -> {
                            log.info("redis Ping 数据包");
                        });
                    });

                    log.info("redis 创建成功");
                    conn.exceptionHandler(e -> {
                        log.warn("redis 连接异常中断");
                        attemptReconnect();
                    });


                }).onFailure(e -> {
                    log.warn("redis 连接失败");
                    attemptReconnect();
        });
    }

    private void attemptReconnect() {
        vertx.setTimer(1000, timer -> {
            log.warn("redis 重新连接...");
            createRedisClient(redisOptions);});
    }
}
