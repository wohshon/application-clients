## SpringBoot client for AMQP

### Pre-req

- JDK 11
- AMQ 7.7 

### setup 

- app exposes a rest endpoint to send a message, random UUID generated as corelationId, hardcoded with a `replyToQueue` reply queue.

- app listens to same queue to print out the payload

### Invocation via mvn

- start up app

`mvn clean spring-boot:run -Dspring-boot.run.arguments="--broker.url=amqp://192.168.0.110:5672 --listen.queue=address0.queue1"`

- call rest endpoint 

`curl 192.168.0.110:8080/api/send/hello`

        2020-08-23 16:09:00.640  INFO 16900 --- [enerContainer-1] c.r.a.client.amqspringboot.Controller    : listening on queue address0.queue1
        2020-08-23 16:09:00.641  INFO 16900 --- [enerContainer-1] c.r.a.client.amqspringboot.Controller    : msg: QUIT
        2020-08-23 16:09:00.641  INFO 16900 --- [enerContainer-1] c.r.a.client.amqspringboot.Controller    : correlation Id: fd809378-7256-4cf0-b3dc-6de7c137879e
        2020-08-23 16:09:00.641  INFO 16900 --- [enerContainer-1] c.r.a.client.amqspringboot.Controller    : replyToQueue: address0.replyQueue

### Invocation via jar file

- create jar file

`mvn clean package -DskipTests`


- executing jar file

`java -jar target/amq-springboot-0.0.1-SNAPSHOT.jar -Dbroker.url=amqp://192.168.0.110:5672 -Dlisten.queue=address0.queue1`

