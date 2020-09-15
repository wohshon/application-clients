package com.redhat.apps.client.rhdgspringboot.entity;

import org.infinispan.protostream.annotations.ProtoField;

import lombok.Data;
import lombok.Getter;


@Data
public class PersonEntity extends AbstractEntity<String> {
    
    @ProtoField(number = 1)
    String id;
    @ProtoField(number = 2)
    String name;
    @ProtoField(number = 3)
    String email;
}