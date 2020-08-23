### nodejs Infinispan client

Express JS REST API to interact with a RHDG 8.0.1 cluster

#### Pre req

- running RHDG cluster
- env parameters
- `DG_HOST`, host of dg cluster, defaults values in my lab environment
- `DG_PORT`, port of dg cluster

### Running the app

`npm start`

- Putting a value into the cache, e.g. `cache1`

`curl -X PUT 192.168.0.110:3000/rhdg/put/cache1/hello/world`

- Retriving value

`curl -X GET 192.168.0.110:3000/rhdg/get/cache1/hello`

- Deleting value 

`curl -X DELETE 192.168.0.110:3000/rhdg/delete/cache1/hello`


