package com.netty.demo.vertx;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.impl.RedisClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxRedis {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        Vertx vertx2 = Vertx.vertx();
        Vertx vertx3 = Vertx.vertx();
        RedisVerticle redisVerticle = new RedisVerticle();
        vertx.deployVerticle(redisVerticle);
//          Redis.createClient(
//                vertx,
//                "redis://192.168.80.110:6379")
//                .connect()
//                .onSuccess(conn -> {
//                    System.out.println(conn);
//                    RedisAPI redis = RedisAPI.api(conn);
//                    redis.get("id1").onSuccess(value -> {
//                        System.out.println(value);
//                    });
//                });
    }
}
