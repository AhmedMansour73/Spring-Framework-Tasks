package com.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		/* --------------------- use application context --------------------
		// ApplicationContext not have close() use ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Student student1 = context.getBean("student", Student.class);
		AppService appService = context.getBean("appService", AppService.class);
		
		
		student1.printData();
		
		context.close();
		
		
		/*
		 * scope in bean is singleton(default) all object refer to same memory (object)
		 *  scope may be prototype if you want it make anther object
		 */
		/*
		Student student2 = context.getBean("student", Student.class);
				
		AppService appService1 = context.getBean("appService", AppService.class);

		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student1 == student2);  // true same object
		
		System.out.println(appService);
		System.out.println(appService1);
		System.out.println(appService == appService1);  // false anther object
		*/
		
		/*
		 * use annotation 
		 */

		/*
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotationContext.xml");
		
//		Student student = context.getBean("student", Student.class); // get init 
//		student.printData();
		
		AppService appService = context.getBean("appService", AppService.class);
		appService.startApp();
		
		context.close();
		*/
		
		/*
		 * use java Container 
		 */
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringContainer.class);
		
		AppService appService = context.getBean("appService", AppService.class);
		appService.startApp();
		
		context.close();
		
		
	}

}
