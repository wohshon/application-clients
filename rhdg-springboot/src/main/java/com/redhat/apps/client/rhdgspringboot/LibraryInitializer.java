package com.redhat.apps.client.rhdgspringboot;


import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
@AutoProtoSchemaBuilder(
      includeClasses = {
        com.redhat.apps.client.rhdgspringboot.entity.PersonEntity.class
      },
      schemaFileName = "person.proto", 
      schemaFilePath = "/proto", 
      schemaPackageName = "demo")
public interface LibraryInitializer  extends SerializationContextInitializer {
    
}