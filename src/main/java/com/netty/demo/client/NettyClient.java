package com.netty.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient();

        Queue proxyQueue = new LinkedBlockingDeque();
        for (int i = 0; i < 10; i++) {
            proxyQueue.add(new ProxyInfo("117.43.75.33", 65001, "1d977", "1d977"));
        }
        nettyClient.createClient(10, proxyQueue);
    }

    public void createClient(int clientNum, Queue<ProxyInfo> proxyQueue) throws InterruptedException {
        EventLoopGroup works = new NioEventLoopGroup(32);
        Bootstrap b = new Bootstrap();
        b.group(works)
        .channel(NioSocketChannel.class)
        .handler(new ClientInitializer(proxyQueue));
        for (int i = 0; i < clientNum; i++) {
            b.connect("123.57.234.120", 6041).sync();
        }
    }
}
