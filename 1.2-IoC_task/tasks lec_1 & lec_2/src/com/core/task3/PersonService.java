package com.core.task3;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class PersonService implements UserService {
	
	@Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("Person updated: " + name);
    }
    
    @PostConstruct
    public void startFunction() {
    	System.out.println("Start App");
    }
	
    @PreDestroy
    public void endFunction() {
    	System.out.println("End App");
    }
}
