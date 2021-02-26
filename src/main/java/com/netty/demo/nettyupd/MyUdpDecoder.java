package com.netty.demo.nettyupd;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ByteProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.zip.CRC32;

@Slf4j
public class MyUdpDecoder extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        ByteBuf reveiveBuf = msg.content();
        String hexData = ByteBufUtil.hexDump(reveiveBuf);

        log.info("Reveive Data : {}", hexData);

        String[] message = splitAllMessage(hexData);
        for (int i = 0; i < message.length; i++) {
            log.info("message:{}", message[i]);
        }

        if(hexData.startsWith("aa56")) {
            String sendHex = hexData.substring(0, 20)+"0d";

            ByteBuf sendBuf = ctx.alloc().ioBuffer();
            sendBuf.writeBytes(ByteBufUtil.decodeHexDump(sendHex));
            DatagramPacket packet = new DatagramPacket(sendBuf, msg.sender());
            ctx.channel().writeAndFlush(packet);
        }

        if(hexData.startsWith("aa44")) {
            //一个UDP包的全部字节长度
            int packageLen = reveiveBuf.readableBytes();
            int dataLen = packageLen - 2;
            //读取前面数据字节 去掉2个CRC效验码 + 1个字节0D包尾
            byte[] dateByte = new byte[dataLen];
            reveiveBuf.readBytes(dateByte);
            //读取CRC效验码的值
            int crcValue = reveiveBuf.readUnsignedShort();

            CRC32 crc32 = new CRC32();
            crc32.update(dateByte);
            long dataCrcValue = crc32.getValue();

            //验证错误
            if(crcValue != dataCrcValue) {
                ByteBuf sendBuf = ctx.alloc().ioBuffer();
                sendBuf.writeBytes(ByteBufUtil.decodeHexDump("AA 44 0B 00 00 00 01 02 00 00 0D"));
                DatagramPacket packet = new DatagramPacket(sendBuf, msg.sender());
                ctx.channel().writeAndFlush(packet);
            } else {
                //验证成功
                ByteBuf sendBuf = ctx.alloc().ioBuffer();
                sendBuf.writeBytes(ByteBufUtil.decodeHexDump("AA 44 0B 00 00 00 01 00 00 00 0D"));
                DatagramPacket packet = new DatagramPacket(sendBuf, msg.sender());
                ctx.channel().writeAndFlush(packet);
            }
        }
    }


    private String[] splitAllMessage(final String allMessageHex) {
        String[] message = allMessageHex.split("0d");
        return message;
    }
}
