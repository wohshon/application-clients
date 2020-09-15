package com.redhat.apps.client.rhdgspringboot.services;

import com.redhat.apps.client.rhdgspringboot.entity.PersonEntity;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("PersonEntity")
public class PersonEntityCacheService implements GenericCacheService<String, PersonEntity, String> {
    
    @Autowired
    CacheHelper cacheHelper;

    @Value("${app.cacheName}")
    String cacheName;
        
    @Override
    public PersonEntity delete(String key) {
        RemoteCacheManager rcm = cacheHelper.getRemoteCacheManager();
        return (PersonEntity)rcm.getCache(cacheName).remove(key);
        
    }

    @Override
    public PersonEntity get(String key) {
         
        RemoteCacheManager rcm = cacheHelper.getRemoteCacheManager();
        PersonEntity p = (PersonEntity)rcm.getCache(cacheName).get(key);
        return p;
    }

    @Override
    public PersonEntity put(String key, PersonEntity value) {
        RemoteCacheManager rcm = cacheHelper.getRemoteCacheManager();
        rcm.getCache(cacheName).put(key, value);
        
        return value;
    }

    @Override
    public PersonEntity update(String key, PersonEntity value) {
        RemoteCacheManager rcm = cacheHelper.getRemoteCacheManager();
        //return (PersonEntity)rcm.getCache(cacheName).replace(key, value); //to use with transactional cache
        return (PersonEntity)rcm.getCache(cacheName).put(key, value);
    }
}