package com.spring.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component // make bean in container <bean id="appService" class="com.spring.core.AppService" >
@Lazy   // to stop @PostConstruct , @PreDestroy to run with any bean run if student inject only
public class Student implements SchoolService {

	@Value("${student.name}") // get data from external file
	private String name ;
	private boolean connectaion= false;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void printData() {
		if(connectaion) {
			System.out.println("*** Student DATA ****");
			System.out.println("\t- "+ name );
		}else {
			System.out.println("can't get data 'No Connection'");
		}
	}
	/*
	 * use with init-method this start before bean 
	 */
	@PostConstruct
	public void openConnection()
	{
		System.out.println("Connectaion opend");
		connectaion=true;
	}
	
	/*
	 * use with destroy-method this start after context close 
	 * 
	 */
	@PreDestroy
	public void closeConnection()
	{
		System.out.println("Connectaion Closed");
		connectaion=false;
	}
}
