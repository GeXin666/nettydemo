package com.netty.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 客户端重连示例
 */
@Slf4j
public class NettyConnection {

    private Bootstrap bootstrap = new Bootstrap();
    private SocketAddress addr_;
    private Channel channel_;
    private Timer timer_;

    public NettyConnection(String host, int port) {
        this.addr_ = new InetSocketAddress( host, port );
        this.timer_ = new Timer();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(createNMMessageHandler());
            }});
        scheduleConnect(10);
    }

    public void send(String msg) throws IOException {
        if( channel_ != null && channel_.isActive() ) {
            ByteBuf buf = channel_.alloc().buffer().writeBytes( msg.getBytes() );
            channel_.writeAndFlush( buf );
        } else {
            throw new IOException( "Can't send message to inactive connection");
        }
    }

    public void close() {
        try {
            channel_.close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doConnect() {
        ChannelFuture f = bootstrap.connect(addr_);
        f.addListener( new ChannelFutureListener() {
            @Override public void operationComplete(ChannelFuture future) {
                if( !future.isSuccess() ) {
                    log.warn("client connect server:{} fail.", addr_);
                    future.channel().close();
                    scheduleConnect(500);
                } else {
                    log.info("client connect server:{} success.", addr_);
                    channel_ = future.channel();
                    addCloseDetectListener(channel_);
                    connectionEstablished();
                }
            }

            private void addCloseDetectListener(Channel channel) {
                channel.closeFuture().addListener((ChannelFutureListener) future -> {
                    log.warn("client connectionLost.");
                    scheduleConnect( 500 );
                });
            }
        });
    }

    private void scheduleConnect( long millis ) {
        timer_.schedule( new TimerTask() {
            @Override
            public void run() {
                doConnect();
            }
        }, millis );
    }

    private ChannelHandler  createNMMessageHandler() {
        return new ChannelInboundHandlerAdapter () {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf buf = (ByteBuf)msg;
            buf.release();
            }
        };
    }

    public void connectionEstablished() {
        try {
            send( "hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main( String...args ) {
        NettyConnection conn = new NettyConnection("192.168.80.225", 34567);

        for( ; ; ) {
            try { Thread.sleep( 100 ); } catch( Exception ex ) {}
        }
    }
}
