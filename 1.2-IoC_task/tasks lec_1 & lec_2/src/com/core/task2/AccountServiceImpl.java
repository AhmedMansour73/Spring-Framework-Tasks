package com.core.task2;

import com.core.task1.UserService;

public class AccountServiceImpl implements AccountService{
	
	private UserService userService; 

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void getSavePerson(String name) {
        System.out.println("AccountService calling save...");
        userService.save(name); // delegate to PersonService
    }

}
