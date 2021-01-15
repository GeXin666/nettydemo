package com.netty.demo.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProxyInfo {

    private String ip;
    private int port;
    private String username;
    private String password;
    private String targetIp;
    private int targetPort;
}
