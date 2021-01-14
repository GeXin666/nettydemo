package com.netty.demo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private ScheduledFuture heartbeatFuture;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info("channelRead:{}", ByteBufUtil.hexDump(buf));
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("New Client Active: {}", ctx.channel().toString());

        ByteBuf helloBuf = ctx.alloc().ioBuffer();
        //helloBuf.writeBytes(byte[]);
        ctx.channel().writeAndFlush(helloBuf);


        ByteBuf connectBuf = ctx.alloc().ioBuffer();
        //connectBuf.writeBytes(byte[]);
        ctx.channel().writeAndFlush(connectBuf);

        heartbeatFuture = ctx.channel().eventLoop().scheduleWithFixedDelay(()-> {
            log.info("这里发送心跳->>>");
            //ByteBuf buf = ctx.alloc().ioBuffer();
            //buf.writeBytes(byte[]);
            //ctx.channel().writeAndFlush(buf);
        }, 20, 20, TimeUnit.SECONDS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("Client Inactive: {}", ctx.channel().toString());
        heartbeatFuture.cancel(true);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("exceptionCaught Client:{} cause:{}", ctx.channel().toString(), cause.getMessage());
    }
}
