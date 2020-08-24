package com.redhat.apps.client.rhdgspringboot.entity;

import lombok.Data;
import lombok.Getter;


@Data
public class PersonEntity extends AbstractEntity<String> {
    
    String id;
    String name;
    String email;
}