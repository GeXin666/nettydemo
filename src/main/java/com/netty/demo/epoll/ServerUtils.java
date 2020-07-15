package com.netty.demo.epoll;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerUtils {

    public static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void addChannel(Channel channel) {
        channels.add(channel);
    }

    public static void writeAndFlush(String msg) {
        channels.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
