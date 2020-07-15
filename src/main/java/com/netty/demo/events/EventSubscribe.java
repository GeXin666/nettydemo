package com.netty.demo.events;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventSubscribe {

    @Subscribe
    @AllowConcurrentEvents
    public void processEvent(String event) {
        log.debug(event);
    }
}
