package com.netty.demo.controller;

import com.netty.demo.epoll.ServerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/WebApi")
public class WebApiController {

    @ResponseBody
    @GetMapping("/test")
    public Object test() {
        log.info("WebApi test");
        ServerUtils.writeAndFlush("hello websocket");
        return "success";
    }
}
