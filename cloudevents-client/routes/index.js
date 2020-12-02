var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

const app = require("express")();
const {Receiver} = require("cloudevents");
 
router.post("/", (req, res) => {
  // body and headers come from an incoming HTTP request, e.g. express.js
  const receivedEvent = Receiver.accept(req.headers, req.body);
  console.log(receivedEvent);
});

module.exports = router;
