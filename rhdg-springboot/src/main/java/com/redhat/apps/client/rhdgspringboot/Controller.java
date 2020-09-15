package com.redhat.apps.client.rhdgspringboot;

import com.redhat.apps.client.rhdgspringboot.entity.PersonEntity;
import com.redhat.apps.client.rhdgspringboot.services.GenericCacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class Controller implements GenericController<String, PersonEntity, String> {

    @Autowired
    @Qualifier("PersonEntity")
    GenericCacheService<String,PersonEntity,String> cacheService;

    //curl -X GET -H "Content-Type: application/json" 192.168.0.110:8080/api/get/1
    @Override
    @GetMapping("/get/{key}")
    public PersonEntity get(@PathVariable String key) {
        log.info("Get : {}", key);
        
        return cacheService.get(key);
    }

    //curl -X PUT -H "Content-Type: application/json" -d '{"id":"1","name":"1","email":"1"}' 192.168.0.110:8080/api/put/1

    @Override
    @PutMapping("/put/{key}")
    public PersonEntity put(@PathVariable String key, @RequestBody PersonEntity value) {
        log.info("Put {}:{}", key, value);
        return cacheService.put(key, value);
    }
    @Override
    @DeleteMapping("/delete/{key}")
    public PersonEntity delete(@PathVariable String key) {
        log.info("Delete : {}", key);
        return cacheService.delete(key);
    }

    @Override
    @PostMapping("/update/{key}")
    public PersonEntity update(@PathVariable String key, @RequestBody PersonEntity value) {
        log.info("Update : {}", key);
        log.info("value {} {}", value.getName(), value.getEmail());
        return cacheService.update(key, value);
    }


}