package com.redhat.apps.quarkus.kafka;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicWriter {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    @Channel("send-to-topic")
    Emitter<String> topicEmitter;

    @Incoming("send-msg")
    public String read(String msg) {
        log.info("received in incoming {}", msg);
        topicEmitter.send(msg);
        return msg;
    }    


}
