package com.netty.demo.epoll;

import com.netty.demo.config.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;

@Slf4j
public class MyHandler extends SimpleChannelInboundHandler<ByteBuf> {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        log.info(ByteBufUtil.hexDump((ByteBuf) msg));
//        ReferenceCountUtil.release(msg);
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().isWritable() + "");
        super.channelWritabilityChanged(ctx);
    }
}
