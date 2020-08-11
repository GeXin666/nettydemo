package com.netty.demo.pb;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class ProtoTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        ImServerProto.ImLogin imLogin = ImServerProto.ImLogin.newBuilder().setJwtToken("TOKEN123").build();

        ImServerProto.ImServerMessage message = ImServerProto.ImServerMessage.newBuilder()
                .setMsgType(ImServerProto.ImServerMessage.MsgType.ImLogin)
                .setImLogin(imLogin).build();

        byte[] content = message.toByteArray();

        System.out.println(Arrays.toString(content));

        ImServerProto.ImServerMessage message1 = ImServerProto.ImServerMessage.getDefaultInstance().newBuilderForType().mergeFrom(content).build();
        if(message1.getMsgType() == ImServerProto.ImServerMessage.MsgType.ImLogin) {
            System.out.println(message1.getImLogin().toString());
        }
    }
}
