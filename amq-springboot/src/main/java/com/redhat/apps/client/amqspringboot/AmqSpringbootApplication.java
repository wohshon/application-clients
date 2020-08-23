package com.redhat.apps.client.amqspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmqSpringbootApplication {

	public static void main(String[] args) {
        for(String arg:args) {
            System.out.println(arg);
        }		
		SpringApplication.run(AmqSpringbootApplication.class, args);
	}

}
