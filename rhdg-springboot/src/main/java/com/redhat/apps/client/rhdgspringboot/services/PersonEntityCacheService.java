package com.redhat.apps.client.rhdgspringboot.services;

import com.redhat.apps.client.rhdgspringboot.entity.PersonEntity;

import org.springframework.stereotype.Component;

@Component("PersonEntity")
public class PersonEntityCacheService implements GenericCacheService<String, PersonEntity, String> {
    


    @Override
    public String delete(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PersonEntity get(String key) {
        PersonEntity p = new PersonEntity();
        p.setEmail("a@b.com");
        p.setId("001");
        p.setName("dude");
        return p;
    }

    @Override
    public PersonEntity put(String key, PersonEntity value) {
        // TODO Auto-generated method stub
        return null;
    }
}