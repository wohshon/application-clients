## Sample Java AMQP client for AMQ 7.7 

Simple java POJO AMQP client for testing of AMQ Broker, covers producing and consuming usecases

#### Setup

- JDK 11
- amqp jms clients

#### Parameters  (values are set as defaults to my lab env)

- broker.url, URL to connect to , e.g. `amqp://hostname:port/` , supports failover url as well
- user.id
- password 
- message, payload in String
- mode, SEND or RECV to indicate sending or consuming type of client to invoke
- loop, for sending, how many messages to send
- delay, for sending, delay between sending , in ms
- queue, queue name 

##### SSL endpoint

- To connect to a SSL endpoint, e.g. over TLS route in OCP, you will need additional parameters, `amqps://` , and trust store locations etc

e.g. :

`amqps://192.168.0.110:5673?transport.trustStoreLocation=./client.ts&transport.trustStorePassword=password&transport.verifyHost=false`

- Refer to this sample [ssl client](https://github.com/wohshon/simple-amq-client/tree/ssl-client)

- //TODO, update SSL test case here


##### Consumer 
- Consumer runs in a loop, it quits when a message "QUIT" is received.

#### Running using mvn commands

- Listening to a queue (using default values for host, queuename etc)

`mvn clean compile exec:java -Dexec.mainClass="com.redhat.apps.clients.App" -Dmode=RECV`

- Sending to a queue the message 'Hello' 5 times, with a delay of 1s

`mvn exec:java -Dexec.mainClass="com.redhat.apps.clients.App" -Dmessage=hello -Dloop=5 -Ddelay=1000`

#### package and run as jar file

- Create jar file

`mvn clean package`

- Listening to message 

`java -jar target/amq-1.0-SNAPSHOT-jar-with-dependencies.jar -Dmode=RECV`

- Sending messages 

`java  -Dmessage=Hello -Dloop=5 -Ddelay=100 -jar target/amq-1.0-SNAPSHOT-jar-with-dependencies.jar `


### JMS POOL library

`PooledJMSApp` client is available as well, same parameters accepted , only supports `mvn` invocation

- additional param , `conn.max` to set maximum connections

e.g 

- Listening

`mvn clean compile  exec:java -Dexec.mainClass="com.redhat.apps.clients.PooledJMSApp" -Dmode=RECV -Dconn.max=5`

- Sending 

`mvn clean compile  exec:java -Dexec.mainClass="com.redhat.apps.clients.PooledJMSApp" -Dmessage=test -Dloop=1 -Ddelay=1000 -Dconn.max=5`