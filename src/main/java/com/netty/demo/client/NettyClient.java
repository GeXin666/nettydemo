package com.netty.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        NettyClient nettyClient = new NettyClient();

        for (int i = 0; i < 1; i++) {
            //创建代理信息
            ProxyInfo proxyInfo = new ProxyInfo("117.43.75.33", 65001, "1d977", "1d977", "39.106.204.3", 8080);
            //创建一个客户端连接
            Channel channel = nettyClient.createClient(proxyInfo);
            //把连接加入组(连接关闭自动从组内移除)
            channels.add(channel);
        }

        //进程停止时调用此方法
        //nettyClient.stopNettyClient();

        //关闭所有客户端
        //channels.close(ChannelMatchers.all());
    }

    /**
     * 线程组(不要设置过多.默认为CPU核数x2，可以x4)
     */
    private EventLoopGroup works = new NioEventLoopGroup(32);

    public Channel createClient(ProxyInfo proxyInfo) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(works)
        .channel(NioSocketChannel.class)
        .handler(new ClientInitializer(proxyInfo));
        Channel channel = b.connect(proxyInfo.getTargetIp(), proxyInfo.getTargetPort()).sync().channel();
        return channel;
    }

    /**
     * 关闭线程组(在系统关闭时调用)
     * 关闭后NettyClient对象不可以在调用createClient创建客户端连接
     */
    public void stopNettyClient() {
        works.shutdownGracefully();
    }
}
