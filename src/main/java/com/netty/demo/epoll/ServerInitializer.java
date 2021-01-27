package com.netty.demo.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("trafficShaping", new ChannelTrafficShapingHandler(1024*1024, 1024*1024, 1000));
        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        pipeline.addLast("delimiter", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, delimiter));
        pipeline.addLast("myhandler", new MyHandler());
    }
}
