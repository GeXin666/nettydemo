package com.netty.demo.proto.server;

import com.netty.demo.pb.ImServerProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImLoginHandler extends SimpleChannelInboundHandler<ImServerProto.ImLogin> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ImServerProto.ImLogin msg) throws Exception {
        log.info(msg.toString());
    }
}
