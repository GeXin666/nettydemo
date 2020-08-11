package com.netty.demo.proto.client;

import com.netty.demo.pb.ImServerProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientPbHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // ImServerProto.ImLogin imLogin = ImServerProto.ImLogin.newBuilder().setJwtToken("TOKEN123").build();
        ImServerProto.ImServerResponse response = ImServerProto.ImServerResponse.newBuilder().setMessage("aaaa").build();

        ImServerProto.ImServerMessage message = ImServerProto.ImServerMessage.newBuilder()
                .setMsgType(ImServerProto.ImServerMessage.MsgType.ImServerResponse)
                .setImServerResponse(response).build();

        ctx.channel().writeAndFlush(message);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(msg.toString());
    }
}
