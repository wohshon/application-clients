package com.redhat.apps.client.amqspringboot.config;

import java.util.HashMap;
import java.util.Map;

import org.amqphub.spring.boot.jms.autoconfigure.AMQP10JMSConnectionFactoryCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ComponentScan
@Slf4j
public class Config {

    @Value("${broker.url}")
    String brokerURL;

    @Bean
    public AMQP10JMSConnectionFactoryCustomizer myAMQPConfig() {
        return (factory) -> {
            log.info("setting up amqp connection factory");
            Map<String, String> props=new HashMap<String, String>();
            props.put("loadBalancingPolicyClassName", "org.apache.activemq.artemis.api.core.client.loadbalance.RoundRobinConnectionLoadBalancingPolicy");
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setPopulateJMSXUserID(true);
            //factory.setRemoteURI("failover:(amqp://192.168.0.110:5672,amqp://192.168.0.110:6672)");
            factory.setProperties(props);
            factory.setRemoteURI(brokerURL);
            // Other options such as custom SSLContext can be applied here
            // where they might otherwise be difficult to set via properties file or URI.


        };

    }   
}