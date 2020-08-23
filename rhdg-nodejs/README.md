### nodejs Infinispan client

ExpressJS REST API to interact with a RHDG 8.0.1 cluster, supports put, get , delete

#### Pre req

- running RHDG cluster
- set env parameters before running 
    - `DG_HOST`, host of dg cluster, defaults to values in my lab environment
    - `DG_PORT`, port of dg cluster

### Running the app

`npm start`

- Putting a value into the cache `cache1`, with key `hello` and value `world`

`curl -X PUT 192.168.0.110:3000/rhdg/put/cache1/hello/world`

- Retriving value, from cache `cache`, key `hello`.

`curl -X GET 192.168.0.110:3000/rhdg/get/cache1/hello`

- Deleting value of key `hello` from cache `cache1`

`curl -X DELETE 192.168.0.110:3000/rhdg/delete/cache1/hello`


