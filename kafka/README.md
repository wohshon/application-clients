
# Quarkus client for kafka using smallrye


Sample app to have a end to end flow to read / write to Kafka , not really reactive, just something to test out the connectivity.

- Exposes a rest endpoint to drop a message into kafka and streamed the text on a web page. 

		/api/send/{msg}

1. drops message into kafka `topic1`, via smallrye
2. picked up by smallrye and streams to web page at `msg.html`, http://host:8080/msg.html
or the raw data at http://host:8080/api/stream

You gotta have a kafka instance running, adjust the connectivity details at `applications.properties`.


### keeping the original quickstart's readme for reference

-----------------------------------------------------------------------------
This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `kafka-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/kafka-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/kafka-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
