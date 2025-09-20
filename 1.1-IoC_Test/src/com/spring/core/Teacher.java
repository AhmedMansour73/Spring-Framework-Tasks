package com.spring.core;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary // inject ust teacher if else select anther service by @Qualifier() 
@Scope("prototype")
public class Teacher implements SchoolService{

	@Override
	public void printData() {
		System.out.println("--- Teacher DATA ---");
		
	}

}
