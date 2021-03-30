package com.netty.demo.epoll;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public class MyHandler extends SimpleChannelInboundHandler<Map<String, String>> {

    public static MyHandler INSTANCE = new MyHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Map<String, String> msg) {
        log.info(msg.toString());
    }
}
