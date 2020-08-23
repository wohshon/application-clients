var express = require('express');
var router = express.Router();
var infinispan = require('infinispan');

//GET value from cache
//curl -X GET 192.168.0.110:3000/rhdg/get/cache1/hello

router.get('/get/:cache/:key', function(req, res, next) {
  var connected = infinispan.client({port: process.env.DG_PORT || 11222, host: process.env.DG_HOST || '192.168.0.110'}, {cacheName: req.params.cache});
  console.log(connected);
  connected.then(function (client) {
    
    console.log('Connected to '+req.params.cache);
    
    var clientGet = client.get(req.params.key);

    var showGet = clientGet.then(
      function(value) { 
        console.log('get(key)=' + value); 
        res.send(value);
      });
      return showGet.finally(
        function() { return client.disconnect(); }); 
    //client.disconnect();
  
  }).catch(function(error) {
  
    console.log("Got error: " + error.message);
  
  });
 // res.send(200);
});

//PUT value to cache
//curl -X PUT 192.168.0.110:3000/rhdg/put/cache1/hello/world

router.put('/put/:cache/:key/:val', function(req, res, next) {
  var connected = infinispan.client({port: process.env.DG_PORT || 11222, host: process.env.DG_HOST || '192.168.0.110'}, {cacheName: req.params.cache});
  console.log(connected);
  connected.then(function (client) {
    
    console.log('Connected to '+req.params.cache);
    var clientPut = client.put(req.params.key, req.params.val); 
    return clientPut.finally(
      function() { return client.disconnect(); }); 
    //client.disconnect();
  
  }).catch(function(error) {
  
    console.log("Got error: " + error.message);
  
  });
    
  res.send(200);
});

//DELETE value from cache
//curl -X DELETE 192.168.0.110:3000/rhdg/delete/cache1/hello

router.delete('/delete/:cache/:key', function(req, res, next) {
  var connected = infinispan.client({port: process.env.DG_PORT || 11222, host: process.env.DG_HOST || '192.168.0.110'}, {cacheName: req.params.cache});
  console.log(connected);
  connected.then(function (client) {
    
    console.log('Connected to '+req.params.cache);
    var clientRemove = client.remove(req.params.key);

  var showRemove = clientRemove.then(
      function(success) { 
        console.log('remove(key)=' + success); 
        res.send(success);      
      },  function(error) { 
        console.log('remove(key)=' + error); 
        res.send(error);      
      });

      return showRemove.finally(
        function() { return client.disconnect(); });    
      //return client.disconnect();
  
  
  }).catch(function(error) {
  
    console.log("Got error: " + error.message);
  
  });
    
});
module.exports = router;
