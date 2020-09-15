package com.redhat.apps.client.rhdgspringboot;

import com.redhat.apps.client.rhdgspringboot.entity.GenericEntity;

public interface GenericController<K, V extends GenericEntity<T>, T> {


    V get(K key);
    V put(K key, V value);
    V delete(K key);
    V update(K key, V value);
}