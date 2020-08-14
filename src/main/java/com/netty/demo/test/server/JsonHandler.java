package com.netty.demo.test.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info(msg);
        ChannelUtils.channels.writeAndFlush("{type:login}");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelUtils.channels.add(ctx.channel());
        super.channelActive(ctx);
    }

}
