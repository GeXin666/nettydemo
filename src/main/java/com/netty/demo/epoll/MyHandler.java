package com.netty.demo.epoll;

import com.netty.demo.config.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class MyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(ByteBufUtil.hexDump((ByteBuf) msg));
        SpringUtils.getBean(TaskExecutor.class).execute(()->{
            log.info("test");
        });
        ReferenceCountUtil.release(msg);
    }
}
