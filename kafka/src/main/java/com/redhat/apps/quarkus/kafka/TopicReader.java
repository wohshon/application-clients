package com.redhat.apps.quarkus.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.reactive.messaging.annotations.Broadcast;

public class TopicReader {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Incoming("msg")
    @Outgoing("data-stream") //send to webpage
    @Broadcast
    public String read(String msg) {
        log.info("incoming {}", msg);
        return msg;
    }
    
}
