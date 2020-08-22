package com.redhat.apps.clients;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */

@Data
@Slf4j
// @NoArgsConstructor
public class App {
    public static void main(String[] args) {
        // mvn exec:java -Dexec.mainClass="com.redhat.apps.clients.App"
        // -Dbroker.url=tcp://192.....
        log.info("started");
        Properties props = new Properties();
        props.setProperty("brokerURL", System.getProperty("broker.url") != null ? System.getProperty("broker.url")
                : "amqp://192.168.0.110:5672");
        props.setProperty("userId", System.getProperty("user.id") != null ? System.getProperty("user.id") : "admin");
        props.setProperty("password",
                System.getProperty("user.password") != null ? System.getProperty("user.password") : "admin");
        props.setProperty("message", System.getProperty("message") != null ? System.getProperty("message") : "hello");
        props.setProperty("mode", System.getProperty("mode") != null ? System.getProperty("mode") : "SEND");
        props.setProperty("loop", System.getProperty("loop") != null ? System.getProperty("loop") : "1");
        props.setProperty("delay", System.getProperty("delay") != null ? System.getProperty("delay") : "0");
        props.setProperty("queue",
                System.getProperty("queue") != null ? System.getProperty("queue") : "address0.queue1");
        App client = new App(props);
        client.connect();

    }

    public App(Properties props) {
        brokerURL = props.getProperty("brokerURL");
        userId = props.getProperty("userId");
        password = props.getProperty("password");
        mode = props.getProperty("mode");
        queue = props.getProperty("queue");
        loop = Integer.parseInt(props.getProperty("loop"));
        delay = Integer.parseInt(props.getProperty("delay"));
        message = props.getProperty("message");
    }

    String brokerURL;
    String userId;
    String password;
    String mode;
    String message;
    int loop,delay;
    String queue;
    Queue jmsQueue;
    ConnectionFactory connectionFactory;
    Connection connection = null;
    Session session;

    void connect() {
        log.info("Connecting to {}, queue {} for {}", brokerURL, queue, mode);
        // Create connection
        try {
            connectionFactory = new JmsConnectionFactory(this.brokerURL);
            connection = connectionFactory.createConnection(this.userId, this.password);
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            jmsQueue = session.createQueue(queue);
            connection.start();

            if (mode.equals("SEND")) {
                MessageProducer sender = session.createProducer(jmsQueue);
                this.send(sender);
            } else {
                MessageConsumer consumer = session.createConsumer(jmsQueue);
                recv(consumer);
            }
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                    log.info("closing connection...");
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    }

    private void send(MessageProducer sender) throws JMSException {
        int i = 0;
        log.info("Sending messages .... {} for {} times, in intervals of {} ms", message, loop, delay);

        while (i < loop) {
            sender.send(session.createTextMessage(message));
            i++;
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        log.info("sent!");
    }

    private void recv(MessageConsumer consumer) throws JMSException {
        TextMessage msg = null;
        String t = "";
        log.info("Listeing for messages.... ");

        while (!t.equals("QUIT") ) {
            msg = (TextMessage)consumer.receive();

            log.info("recevied : {}", msg.getText());
            t = msg.getText();
        }
        log.info("Quiting consumer");

    }
}
