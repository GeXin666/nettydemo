package com.netty.demo.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class ClientInitializer extends ChannelInitializer<Channel> {
    public ClientInitializer() {
    }

    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline().addLast("clientHandler", new ClientHandler());
    }
}
