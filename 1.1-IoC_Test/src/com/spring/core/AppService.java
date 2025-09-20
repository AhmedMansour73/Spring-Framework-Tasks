package com.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AppService {
	
	private SchoolService schoolService;

	@Autowired // by default is exists on constructor  
	public AppService(@Qualifier("studentSer") SchoolService schoolService) {
		this.schoolService = schoolService;
	}
	
	void startApp() {
		System.out.println("Start app school");
		schoolService.printData();
		System.out.println("End app school");
	}
	
	

}
