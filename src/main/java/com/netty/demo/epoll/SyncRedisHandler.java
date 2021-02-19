package com.netty.demo.epoll;

import com.netty.demo.vertx.JedisUtil;
import com.netty.demo.vertx.RedisVerticle;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.vertx.redis.client.RedisAPI;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

@Slf4j
public class SyncRedisHandler extends SimpleChannelInboundHandler<HttpRequest> {

    private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };
    private static AtomicInteger counter = new AtomicInteger();

    private static Executor executor = Executors.newFixedThreadPool(4);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest req) {
        log.info("count: " + counter.addAndGet(1));

        boolean keepAlive = HttpUtil.isKeepAlive(req);
        FullHttpResponse response = new DefaultFullHttpResponse(req.protocolVersion(), HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(CONTENT));
        response.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN)
                .setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());

        if (keepAlive) {
            if (!req.protocolVersion().isKeepAliveDefault()) {
                response.headers().set(CONNECTION, KEEP_ALIVE);
            }
        } else {
            // Tell the client we're going to close the connection.
            response.headers().set(CONNECTION, HttpHeaderValues.CLOSE);
        }


        executor.execute(()->{
            JedisUtil.incr("count");
            ChannelFuture f = ctx.writeAndFlush(response);
            if (!keepAlive) {
                f.addListener(ChannelFutureListener.CLOSE);
            }
        });
    }
}
