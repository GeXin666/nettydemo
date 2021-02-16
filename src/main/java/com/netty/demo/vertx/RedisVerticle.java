package com.netty.demo.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.redis.client.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisVerticle extends AbstractVerticle {

    private static final int MAX_RECONNECT_RETRIES = 16;

    private final RedisOptions options = new RedisOptions();

    public RedisAPI redis;

    @Override
    public void start() {
        log.info("start");
        createRedisClient().onSuccess(conn -> redis = RedisAPI.api(conn));
    }

    /**
     * Will create a redis client and setup a reconnect handler when there is
     * an exception in the connection.
     */
    private Future<RedisConnection> createRedisClient() {
        Promise<RedisConnection> promise = Promise.promise();
        RedisOptions options = new RedisOptions().setConnectionString("redis://192.168.80.110:6379")
                // allow at max 8 connections to redis
                .setMaxPoolSize(8)
                // allow 32 connection requests to queue waiting
                // for a connection to be available.
                .setMaxWaitingHandlers(32);

        Redis.createClient(vertx, options)
                .connect()
                .onSuccess(conn -> {
                    log.info("连接成功 : {}", conn);
                    conn.exceptionHandler(e -> attemptReconnect(0));
                    promise.complete(conn);
                }).onFailure(h -> {
                    h.printStackTrace();
                    attemptReconnect(0);
        });
        return promise.future();
    }

    /**
     * Attempt to reconnect up to MAX_RECONNECT_RETRIES
     */
    private void attemptReconnect(int retry) {
        log.debug("Redis 从新连接.....");
        if (retry > MAX_RECONNECT_RETRIES) {
            // we should stop now, as there's nothing we can do.
        } else {
            // retry with backoff up to 10240 ms
            long backoff = (long) (Math.pow(2, Math.min(retry, 10)) * 10);

            vertx.setTimer(backoff, timer -> {
                createRedisClient().onFailure(t -> attemptReconnect(retry + 1));
            });
        }
    }
}
