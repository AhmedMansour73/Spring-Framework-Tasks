package com.core.task3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AppMainUser {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonService personService = context.getBean("personServiceTask3",PersonService.class);
		PersonService personService1 = context.getBean("personServiceTask3",PersonService.class);
		
		personService.save("Ahmed");
		personService.update("Ali");
		
		System.out.println("");
		System.out.println(personService);
		System.out.println(personService1);
		System.out.println(personService == personService1);
		
		context.close();
		
		
		System.out.println("------------------------------------");
		System.out.println("--  Use Java Container No xml     --");
		System.out.println("------------------------------------");
		
		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext(TaskOneContainer.class);
		
		PersonService personService2 = context1.getBean("personService",PersonService.class);
		
		personService2.save("Ahmed");
		personService2.update("Ali");
		
		context1.close();
		
	}

}
