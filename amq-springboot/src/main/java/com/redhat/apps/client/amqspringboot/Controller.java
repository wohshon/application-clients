package com.redhat.apps.client.amqspringboot;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@EnableJms
@RequestMapping(value = "/api")
public class Controller {


    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/test")
    public void test() {
        log.info("hello");

    }
    @GetMapping("/send/{msg}")
    public String send(@PathVariable String msg) {
        log.info("sending msg: "+msg);
        //this.jmsTemplate.convertAndSend("address0.queue1", msg);
        final String correlationId = UUID.randomUUID().toString();
        this.jmsTemplate.convertAndSend("address0.queue1", msg, new CorrelationIdPostProcessor(correlationId));
        log.info("sent!");
        return msg;

    }

    @Value("${listen.queue}")
    private String lqueue;

    @JmsListener(destination = "${listen.queue}")
    public void listen(Message msg) {
        try {
            log.info("listening on queue {}", lqueue);
            log.info("msg: {}", ((TextMessage) msg).getText());
            log.info("correlation Id: {}",((TextMessage) msg). getJMSCorrelationID());
            log.info("replyToQueue: {}",((TextMessage) msg).getJMSReplyTo());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}