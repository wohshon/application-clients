package com.redhat.apps.client.rhdgspringboot.services;

import org.infinispan.client.hotrod.RemoteCacheManager;

import lombok.Data;

@Data
public class CacheHelper {
    
    RemoteCacheManager rcm;
    RemoteCacheManager getRemoteCacheManager() {


        return null;
    }
}