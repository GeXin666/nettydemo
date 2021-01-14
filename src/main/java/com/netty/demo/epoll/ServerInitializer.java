package com.netty.demo.epoll;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolConfig;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) {
        //ch.pipeline().addLast("log", new LoggingHandler(LogLevel.INFO));
//        ch.pipeline().addLast("http-codec", new HttpServerCodec());
//        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(Integer.MAX_VALUE));
//        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        ch.pipeline().addLast("handler", new MyHandler());
    }
}
