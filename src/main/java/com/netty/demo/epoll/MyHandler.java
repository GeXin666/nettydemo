package com.netty.demo.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

@Slf4j
public class MyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //log.info("msg:{}", msg.toString());
        ReferenceCountUtil.release(msg);
        //ByteBuf buf = ctx.alloc().heapBuffer(1024*1024*10);
        ByteBuf buf = ctx.alloc().directBuffer(1024);
        //log.info(buf.toString());
        //ReferenceCountUtil.release(buf);


        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK,
                Unpooled.wrappedBuffer("hello".getBytes()));
        response.headers()
                .set(CONTENT_TYPE, TEXT_PLAIN)
                .setInt(CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(CONNECTION, KEEP_ALIVE);
        ctx.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
