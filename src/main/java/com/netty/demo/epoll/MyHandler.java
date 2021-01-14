package com.netty.demo.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Slf4j
public class MyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        log.info(buf.toString(CharsetUtil.UTF_8));
//        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(ksCommon.getKsAccount().getProxyIP(), ksCommon.getKsAccount().getSocketPort()));
//
//        socket = new Socket(proxy);
//        socket.connect(new InetSocketAddress(startPlay.getSocketIp(), startPlay.getSocketPort()));
//        ksCommon.setSocket(socket);
    }
}
