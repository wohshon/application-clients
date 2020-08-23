package com.redhat.apps.client.amqspringboot;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import org.springframework.jms.core.MessagePostProcessor;

public class CorrelationIdPostProcessor implements MessagePostProcessor {
    
    private final String correlationId;

    public CorrelationIdPostProcessor(final String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public Message postProcessMessage(final Message msg) throws JMSException {
        msg.setJMSCorrelationID(correlationId);
        msg.setJMSReplyTo(
            new Queue(){
                
                @Override
                public String getQueueName() throws JMSException {
                    
                    return "address0.replyQueue";
                }
            });
        return msg;
    }
}