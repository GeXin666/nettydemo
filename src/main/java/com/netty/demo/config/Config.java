package com.netty.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    public static int serverPort;

    @Value("${netty.server.port}")
    public void setStationPort(int stationPort) {
        Config.serverPort = stationPort;
    }
}
