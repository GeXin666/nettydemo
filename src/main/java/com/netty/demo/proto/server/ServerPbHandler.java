package com.netty.demo.proto.server;

import com.google.protobuf.MessageLite;
import com.netty.demo.pb.ImServerProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerPbHandler extends SimpleChannelInboundHandler<MessageLite> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageLite msg) throws Exception {
        if(msg instanceof ImServerProto.ImLogin) {
            ImServerProto.ImLogin login = (ImServerProto.ImLogin) msg;
            log.debug(login.toString());
            ctx.channel().writeAndFlush(login);
        }
    }
}
