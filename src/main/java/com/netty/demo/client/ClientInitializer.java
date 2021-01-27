package com.netty.demo.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

public class ClientInitializer extends ChannelInitializer<Channel> {
    public ClientInitializer() {
    }

    @Override
    protected void initChannel(Channel ch) {
        //ch.pipeline().addLast("traffic", new ChannelTrafficShapingHandler(1024*1024, 1024*1024, 1000));
        ch.pipeline().addLast("clientHandler", new ClientHandler());
    }
}
