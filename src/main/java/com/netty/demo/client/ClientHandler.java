package com.netty.demo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger SEQ = new AtomicInteger();
    private final byte[] ECHO_REQ = new byte[1024 * 1024];
    private final String DELIMITER = "$_";
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info("channelRead:{}", ByteBufUtil.hexDump(buf));
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("New Client Active: {}", ctx.channel().toString());
        executorService.scheduleAtFixedRate(()->{
            for (int i = 0; i < 10; i++) {
                ByteBuf buf = Unpooled.copiedBuffer(ECHO_REQ, DELIMITER.getBytes());
                SEQ.getAndAdd(buf.readableBytes());
                if(ctx.channel().isWritable()) {
                    ctx.channel().write(buf);
                } else {
                    log.warn("channel is not writeable :" + ctx.channel().unsafe().outboundBuffer().nioBufferSize());
                }
                ctx.flush();
                int counter = SEQ.getAndSet(0);
                log.info("the client is send rate is " + counter + "bytes/s");
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("Client Inactive: {}", ctx.channel().toString());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("exceptionCaught Client:{} cause:{}", ctx.channel().toString(), cause.getMessage());
    }
}
