package com.netty.demo.epoll;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler.HandshakeComplete;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class WebSocketEvent extends ChannelInboundHandlerAdapter {

    public static final WebSocketEvent INSTANCE = new WebSocketEvent();

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof HandshakeComplete) {
            HandshakeComplete handshakeEvent = (HandshakeComplete) evt;
            log.debug(handshakeEvent.requestUri());
            log.debug(handshakeEvent.requestHeaders().toString());
            //add to group
            ServerUtils.addChannel(ctx.channel());
            ctx.channel().attr(AttributeKey.valueOf("USERID")).set("xxxxxxxxx");
        }
        super.userEventTriggered(ctx, evt);
    }
}
