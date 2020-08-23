### Node JS AMQP client

- set the following parameters

        var conn_url = url.parse(process.env.BROKER_URL || 'amqp://192.168.0.110:5672');
        var address = process.env.ADDRESS || 'address0.queue1';

- start app, listens on 3000

`DEBUG=amq-nodejs:* npm start`

- to send a `hello` message 

`curl http://host:port/amqp/send/hello`

- to start receving messages, can be turned off by sending a `QUIT` message 

`curl http://host:port/amqp/recv`