package com.netty.demo.proto.client;

import com.netty.demo.pb.ImServerProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientPbHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ImServerProto.ImLogin imLogin = ImServerProto.ImLogin.newBuilder().setJwtToken("TKONE123").build();
        ctx.channel().writeAndFlush(imLogin);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(msg.toString());
    }
}
