package com.netty.demo.epoll.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class OutboundHandlerA extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info(msg.toString());
        ByteBuf buf = ctx.alloc().ioBuffer(32);
        buf.writeBytes(msg.toString().getBytes("utf-8"));
        super.write(ctx, buf, promise);

        ctx.channel().eventLoop().scheduleWithFixedDelay(()->{
            //这里写发送逻辑
        },0, 1, TimeUnit.SECONDS);
    }
}
