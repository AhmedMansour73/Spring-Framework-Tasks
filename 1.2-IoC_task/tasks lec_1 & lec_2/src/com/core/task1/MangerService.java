package com.core.task1;

import org.springframework.stereotype.Component;

@Component
public class MangerService implements UserService{
	@Override
    public void save(String name) {
        System.out.println("Manager saved: " + name);
    }

    @Override
    public void update(String name) {
        System.out.println("Manager updated: " + name);
    }

}
