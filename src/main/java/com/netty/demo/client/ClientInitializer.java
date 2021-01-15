package com.netty.demo.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.proxy.Socks5ProxyHandler;

import java.net.InetSocketAddress;

public class ClientInitializer extends ChannelInitializer<Channel> {

    public ProxyInfo proxyInfo;

    public ClientInitializer(ProxyInfo proxyInfo) {
        this.proxyInfo = proxyInfo;
    }

    @Override
    protected void initChannel(Channel ch) {
        InetSocketAddress proxyAddress = new InetSocketAddress(proxyInfo.getIp(), proxyInfo.getPort());
        ch.pipeline().addLast("sock5", new Socks5ProxyHandler(
                proxyAddress, proxyInfo.getUsername(), proxyInfo.getPassword()));
        ch.pipeline().addLast("clientHandler", new ClientHandler());
    }
}
