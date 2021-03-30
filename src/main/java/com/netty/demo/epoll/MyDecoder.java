package com.netty.demo.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyDecoder extends ReplayingDecoder<MyDecoderState> {

    public MyDecoder() {
        super(MyDecoderState.READ_HEAD);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        switch (state()) {
            case READ_HEAD:
                int head = in.readUnsignedShort();
                if(head == 0xA003) {
                    checkpoint(MyDecoderState.DECODERA03);
                }
                if(head == 0xA004) {
                    checkpoint(MyDecoderState.DECODERA04);
                }
                break;
            case DECODERA03:
                Map<String, String> data = new LinkedHashMap<>();
                data.put("head", "A003");

                /* 6 byte */
                byte[] mac = new byte[6];
                in.readBytes(mac);
                data.put("macHex", ByteBufUtil.hexDump(mac));

                /* 32 byte */
                String model = in.readCharSequence(32, CharsetUtil.UTF_8).toString();
                data.put("model", model);

                /* 16 byte */
                String version = in.readCharSequence(16, CharsetUtil.UTF_8).toString();
                data.put("version", version);

                /* 16 byte */
                String hdVersion = in.readCharSequence(16, CharsetUtil.UTF_8).toString();
                data.put("hdVersion", hdVersion);

                /* 32 byte */
                String sn = in.readCharSequence(32, CharsetUtil.UTF_8).toString();
                data.put("sn", sn);

                /* 16 byte */
                String REVISION = in.readCharSequence(16, CharsetUtil.UTF_8).toString();
                data.put("REVISION", REVISION);

                /* 64 byte */
                String NAME = in.readCharSequence(64, CharsetUtil.UTF_8).toString();
                data.put("NAME", NAME);

                /* 64 byte */
                String DESCRIPTION = in.readCharSequence(64, CharsetUtil.UTF_8).toString();
                data.put("DESCRIPTION", DESCRIPTION);

                /* 64 byte */
                String LOCATION = in.readCharSequence(64, CharsetUtil.UTF_8).toString();
                data.put("LOCATION", LOCATION);

                /* 20 byte */
                String IP1ADDR = in.readCharSequence(20, CharsetUtil.UTF_8).toString();
                data.put("IP1ADDR", IP1ADDR);

                /* 20 byte */
                String IP1GATEWAY = in.readCharSequence(20, CharsetUtil.UTF_8).toString();
                data.put("IP1GATEWAY", IP1GATEWAY);

                /* 20 byte */
                String IP1NETMASK = in.readCharSequence(20, CharsetUtil.UTF_8).toString();
                data.put("IP1NETMASK", IP1NETMASK);

                /* 2 byte */
                int IP1VLAN = in.readUnsignedShort();
                data.put("IP1VLAN", IP1VLAN+"");

                /* 20 byte */
                String IP2ADDR = in.readCharSequence(20, CharsetUtil.UTF_8).toString();
                data.put("IP2ADDR", IP2ADDR);

                /* 20 byte */
                String IP2GATEWAY = in.readCharSequence(20, CharsetUtil.UTF_8).toString();
                data.put("IP2GATEWAY", IP2GATEWAY);

                /* 20 byte */
                String IP2NETMASK = in.readCharSequence(20, CharsetUtil.UTF_8).toString();
                data.put("IP2NETMASK", IP2NETMASK);

                /* 2 byte */
                int IP2VLAN = in.readUnsignedShort();
                data.put("IP2VLAN", IP2VLAN+"");

                /* 4 byte */
                long UPTIME = in.readUnsignedInt();
                data.put("UPTIME", UPTIME+"");

                /* 2 byte */
                byte[] crc = new byte[2];
                in.readBytes(crc);
                data.put("crcHex", ByteBufUtil.hexDump(crc));

                checkpoint(MyDecoderState.READ_HEAD);
                out.add(data);
                break;
            case DECODERA04:
                //
                checkpoint(MyDecoderState.READ_HEAD);
                break;
            case DECODERA05:
                checkpoint(MyDecoderState.READ_HEAD);
                break;
            case DECODERA06:
                checkpoint(MyDecoderState.READ_HEAD);
                break;
            case DECODERA07:
                checkpoint(MyDecoderState.READ_HEAD);
                break;
            case DECODERA08:
                checkpoint(MyDecoderState.READ_HEAD);
                break;
            default:
                throw new Error("Shouldn't reach here.");
        }
    }
}
