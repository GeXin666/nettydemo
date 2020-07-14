package com.netty.demo.epoll;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast("log", new LoggingHandler(LogLevel.INFO));
    }
}
