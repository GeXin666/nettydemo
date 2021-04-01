package com.netty.demo.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class MyHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static MyHandler INSTANCE = new MyHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        log.info(ByteBufUtil.hexDump(msg));
    }
}
