package com.netty.demo.proto.server;

import com.netty.demo.pb.ImServerProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImServerResponseHandler extends SimpleChannelInboundHandler<ImServerProto.ImServerResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ImServerProto.ImServerResponse msg) throws Exception {
        log.info(msg.toString());
    }
}
