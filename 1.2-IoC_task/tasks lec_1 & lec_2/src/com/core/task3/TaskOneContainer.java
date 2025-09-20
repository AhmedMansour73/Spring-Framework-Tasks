package com.core.task3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskOneContainer {
	 @Bean
	    public PersonService personService() {
	        return new PersonService();
	    }

}
