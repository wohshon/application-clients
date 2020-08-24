package com.redhat.apps.client.rhdgspringboot.entity;


public interface GenericEntity<T> {
    T getId();
    String getName();
}