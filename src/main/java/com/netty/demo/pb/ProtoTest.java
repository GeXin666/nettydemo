package com.netty.demo.pb;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class ProtoTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        ImServerProto.ImLogin imLogin = ImServerProto.ImLogin.newBuilder().setJwtToken("TOKEN123").build();
        byte[] content = imLogin.toByteArray();

        System.out.println(Arrays.toString(content));

        ImServerProto.ImLogin imLogin1 = ImServerProto.ImLogin.getDefaultInstance().newBuilderForType().mergeFrom(content).build();
        System.out.println(imLogin1.toString());
    }
}
