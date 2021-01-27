package com.netty.demo.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MyHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger counter = new AtomicInteger();

    public ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public MyHandler() {
        executorService.scheduleAtFixedRate(()->{
            log.info("the server receive client rate is " + counter.getAndSet(0) + "bytes/s");
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        counter.getAndAdd(msg.readableBytes());
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().isWritable() + "");
        super.channelWritabilityChanged(ctx);
    }
}
