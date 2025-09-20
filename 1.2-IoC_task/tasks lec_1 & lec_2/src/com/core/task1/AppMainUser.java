package com.core.task1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AppMainUser {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonService personService = context.getBean("personService",PersonService.class);
		MangerService mangerService = context.getBean("mangerService" , MangerService.class);
		
		personService.save("Ahmed");
		personService.update("Ali");
		
		mangerService.save("Mohammed");
		mangerService.update("Walid");
		
		System.out.println("------------------------------------");
		System.out.println("--  Use Java Container No xml     --");
		System.out.println("------------------------------------");
		
		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(TaskOneContainer.class);
		
		PersonService personService1 = context1.getBean("personService",PersonService.class);
		MangerService mangerService1 = context1.getBean("mangerService" , MangerService.class);
		
		personService1.save("Ahmed");
		personService1.update("Ali");
		
		mangerService1.save("Mohammed");
		mangerService1.update("Walid");
	}

}
