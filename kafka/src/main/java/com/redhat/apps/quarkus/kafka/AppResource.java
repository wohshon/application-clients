package com.redhat.apps.quarkus.kafka;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api")
public class AppResource {

    @Inject
    @Channel("data-stream")
    Publisher<String> msg;

    Logger log = LoggerFactory.getLogger(this.getClass());
    
    @ConfigProperty(name = "greeting.msg")
    String greeting;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello/{msg}")
    public String hello(@PathParam String msg) {
        log.info("got message {} {}",greeting,msg);
        return greeting.concat(" ").concat(msg);
    }

    //stream data to web
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    @SseElementType("text/plain") 
    public Publisher<String> stream() { 
        return msg;
    }    

    @Channel("send-msg")
    Emitter<String> msgEmitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/send/{msg}")
    public String send(@PathParam String msg) {
        log.info("got message {}",msg);
        msgEmitter.send(msg);
        return msg;
    }    

    
}