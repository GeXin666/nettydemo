package com.netty.demo.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.io.File;

@Slf4j
public class SSLServerInitializer extends ChannelInitializer {

    private static  SslContext sslCtx;

    static  {
        File certChainFile=new File("/home/certs/nginx.crt");
        File keyFile=new File("/home/certs/pkcs8_rsa_private_key.pem");
        try {
            sslCtx = SslContextBuilder
                .forServer(certChainFile, keyFile)
                //.protocols("TLSv1.3","TLSv.1.2")
                .clientAuth(ClientAuth.NONE)
                .sslProvider(SslProvider.OPENSSL).build();
        } catch (SSLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast("ssl", sslCtx.newHandler(ByteBufAllocator.DEFAULT));
        ch.pipeline().addLast("http", new HttpServerCodec());
        ch.pipeline().addLast("httpag", new HttpObjectAggregator(Integer.MAX_VALUE));
        ch.pipeline().addLast("httphandler", new HttpHandler());
    }
}
