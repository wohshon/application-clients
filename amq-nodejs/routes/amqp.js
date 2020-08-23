var express = require('express');
var rhea = require("rhea");
var url = require("url");

var router = express.Router();
var conn_url = url.parse(process.env.BROKER_URL || 'amqp://192.168.0.110:5672');
var address = process.env.ADDRESS || 'address0.queue1';
var message_body = process.env.MSG || 'Hello';
var message;
var container = rhea.create_container();  

container.on("sender_open", function (event) {
  console.log("SEND: Opened sender for target address '" +
              event.sender.target.address + "'");
});

container.on("sendable", function (event) {


  event.sender.send(message);

  console.log("SEND: Sent message '" + message.body + "'");

  event.sender.close();
  event.connection.close();
});
//==========================================================

container.on("receiver_open", function (event) {
  console.log("RECEIVE: Opened receiver for source address '" +
              event.receiver.source.address + "'");
});

container.on("message", function (event) {
  var message = event.message;

  console.log("RECEIVE: Received message '" + message.body + "'");


  if (message.body == "QUIT") {
      event.receiver.close();
      event.connection.close();
  }
});

//======================================================

var opts = {
  host: conn_url.hostname,
  port: conn_url.port || 5672,
  // To connect with a user and password:
  // username: "<username>",
  // password: "<password>",
};

router.get('/send/:msg', function(req, res, next) {
  var conn = container.connect(opts);
  console.log("Sending message: "+req.params.msg + "to "+conn_url.hostname);
  message = {
    body: req.params.msg
  };  
  conn.open_sender(address);
  res.send(200);
});

router.get('/recv', function(req, res, next) {
  var conn = container.connect(opts);
  conn.open_receiver(address);
  res.send(200);
});

module.exports = router;
