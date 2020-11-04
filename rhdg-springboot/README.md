### Hotrod java client wrapped in SpringBoot app, RHDG 8.1.0


#### Update for internal-client branch

In hot rod connection properties file

- connections endpoints updated to service based 
- create a secret to be mounted into pod. The tls-2.crt is extracted from  `example-infinispan-cert-secret in the project of the infinispan cluster

	$ oc create secret generic dg-crt --from-file=/tmp/tls-2.crt 


- to deploy on OCP 

		$ oc new-app openjdk-11-rhel8:1.0~https://github.com/wohshon/application-clients#internal-client --context-dir=rhdg-springboot --name=client
		$ oc expose svc client

- patch the deployment object to mount the secret, adding the `volumes` and `volumeMounts` portion

e.g.
```
    spec:
      containers:
      - image: image-registry.openshift-image-registry.svc:5000/apps/client@sha256:c881c21c6c55165d1acd918ae8c211d5ab7f96be7842c6dc02cb962206b87e53
        imagePullPolicy: IfNotPresent
        name: client
        ports:
        - containerPort: 8080
          protocol: TCP
        - containerPort: 8443
          protocol: TCP
        - containerPort: 8778
          protocol: TCP
        resources: {}
        terminationMessagePath: /dev/termination-log
        terminationMessagePolicy: File
        volumeMounts:
        - mountPath: /tmp/crt
          name: mydir
      volumes:
      - name: mydir
        secret:
          defaultMode: 420
          secretName: dg-crt

```

- To test the app, invoke the springboot app REST endpoint via the route

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


