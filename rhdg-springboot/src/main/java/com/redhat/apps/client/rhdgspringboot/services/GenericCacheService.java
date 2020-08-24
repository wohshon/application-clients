package com.redhat.apps.client.rhdgspringboot.services;


import com.redhat.apps.client.rhdgspringboot.entity.GenericEntity;

public interface GenericCacheService<K, V extends GenericEntity<T>, T> {
 
    V get(K key);
    V put(K key, V value);
    K delete(K key);
    
}