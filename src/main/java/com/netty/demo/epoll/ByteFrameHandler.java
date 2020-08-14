package com.netty.demo.epoll;

import com.netty.demo.pb.ImServerProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ByteFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        byte[] content = new byte[msg.content().readableBytes()];
        msg.content().readBytes(content);
        log.info(Arrays.toString(content));

        ImServerProto.ImLogin imLogin = ImServerProto.ImLogin.parseFrom(content);
        System.out.println(imLogin.toString());
    }
}
