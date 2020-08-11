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
        ImServerProto.ImServerMessage imServerMessage = (ImServerProto.ImServerMessage) msg;

        if(imServerMessage.getMsgType() == ImServerProto.ImServerMessage.MsgType.ImLogin) {
            ctx.fireChannelRead(imServerMessage.getImLogin());
        }

        if(imServerMessage.getMsgType() == ImServerProto.ImServerMessage.MsgType.ImServerResponse) {
            ctx.fireChannelRead(imServerMessage.getImServerResponse());
        }
    }
}
