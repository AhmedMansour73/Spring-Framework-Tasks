package com.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // make it like container 
@ComponentScan(basePackages = "com.spring.core")  // <context:component-scan>
@PropertySource("classpath:data.properties")     // <context:property-placeholder>
public class SpringContainer {
	
	@Bean
	public Student studentSer() {
		return new Student();
	}

}
