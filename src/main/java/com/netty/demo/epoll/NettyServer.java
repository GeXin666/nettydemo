package com.netty.demo.epoll;

import com.netty.demo.config.Config;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
//@Component
public class NettyServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup(32);

    public static void main(String[] args) throws Exception {
        new NettyServer().start();
    }

    @PostConstruct
    public void start() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.DEBUG))
            .option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                //.childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator())
            //.childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(16, 32))
            .childHandler(new ServerInitializer());
        b.bind("0.0.0.0", Config.serverPort).sync().channel();
        log.info("Netty Server started on port: {}", Config.serverPort);
    }

    @PreDestroy
    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("Netty Server stopped on port: {}", Config.serverPort);
    }
}
