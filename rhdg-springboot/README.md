### Hotrod java client wrapped in SpringBoot app, RHDG 8.1.0


#### Update for internal-client branch

In hot rod connection properties file

- connections endpoints updated to service based 
- removed TLS related config

to deploy on OCP 

		$ oc new-app openjdk-11-rhel8:1.0~https://github.com/wohshon/application-clients#internal-client --context-dir=rhdg-springboot --name=client


#### Simple CRUD usecases based on a `PersonEntity` data type

- Pre-req:
  - Running red hat datagrid instance at endpoint defined in hotrod-client.properties
  - create a cache, enter the cachename in application.properties `cacheName`

- start up app

	`mvn clean compile spring-boot:run`

- PUT entry

	`curl -X PUT -H 'Content-type: application/json' -d '{"id":"001","name":"ws","email": "a@b.com"}' <host>:<port>/api/put/001`


- GET entry

	`curl -X GET <host>:<port>/api/get/001`

- DELETE entry

	`curl -X DELETE <host>:<port>/api/get/001`

- UPDATE entry

	`curl -X POST -H 'Content-type: application/json' -d '{"id":"001","name":"ws","email": "a@b.com"}' <host>:<port>/api/update/001`

#### OpenShift Routes , based on Datagrid Operator for DG 8.1

- Deploy operator, rsh into pod, run command to create cache

- getting the certs 

		oc extract secret/example-infinispan-cert-secret -n ${namespace} --keys=tls.crt --to=/tmp

- update the route names in hotrod properties

- update application.properties to use the correct cache name and properties files


