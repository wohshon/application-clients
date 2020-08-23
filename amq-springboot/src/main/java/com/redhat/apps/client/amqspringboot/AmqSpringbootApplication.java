package com.redhat.apps.client.amqspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AmqSpringbootApplication {

	public static void main(String[] args) {
        for(String arg:args) {
            log.info(arg);
        }		
		SpringApplication.run(AmqSpringbootApplication.class, args);
	}

}
