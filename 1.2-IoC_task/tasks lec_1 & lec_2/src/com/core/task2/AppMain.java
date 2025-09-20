package com.core.task2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.core.task1.MangerService;
import com.core.task1.PersonService;
import com.core.task1.TaskOneContainer;

public class AppMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		AccountService accountService = (AccountService) context.getBean("accountService");
		accountService.getSavePerson("Ahmed Mansour");
		

		System.out.println("------------------------------------");
		System.out.println("--  Use Java Container No xml     --");
		System.out.println("------------------------------------");
		
		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(TaskOneContainer.class);
		AccountService accountService1 = (AccountService) context1.getBean("accountService");
		accountService1.getSavePerson("Ahmed Mohammed");

	}

}
