package com.netty.demo.epoll;

import com.netty.demo.epoll.handler.InboundHandlerA;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(Integer.MAX_VALUE));
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        WebSocketServerProtocolConfig wsConfig = WebSocketServerProtocolConfig.newBuilder()
                .websocketPath("mypath")
                .maxFramePayloadLength(Integer.MAX_VALUE)
                .checkStartsWith(true).build();
        pipeline.addLast("webSocketHandshake", new WebSocketServerProtocolHandler(wsConfig));
        pipeline.addLast("TextHandler", new TextWebSocketHandler());
    }
}
