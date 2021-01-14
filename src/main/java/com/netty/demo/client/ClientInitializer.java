package com.netty.demo.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.proxy.Socks5ProxyHandler;

import java.net.InetSocketAddress;
import java.util.Queue;

public class ClientInitializer extends ChannelInitializer<Channel> {

    public Queue<ProxyInfo> proxyQueue;

    public ClientInitializer(Queue<ProxyInfo> proxyQueue) {
        this.proxyQueue = proxyQueue;
    }

    @Override
    protected void initChannel(Channel ch) {
        ProxyInfo proxy = proxyQueue.poll();
        InetSocketAddress proxyAddress = new InetSocketAddress(proxy.getIp(), proxy.getPort());
        ch.pipeline().addLast("sock5", new Socks5ProxyHandler(
                proxyAddress, proxy.getUsername(), proxy.getPassword()));
        ch.pipeline().addLast("clientHandler", new ClientHandler());
    }
}
