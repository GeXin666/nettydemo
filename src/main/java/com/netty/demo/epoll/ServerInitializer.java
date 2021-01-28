package com.netty.demo.epoll;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

public class ServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("trafficShaping", new ChannelTrafficShapingHandler(1024*1024, 100, 1000));
        pipeline.addLast("myhandler", new MyHandler());
    }
}
