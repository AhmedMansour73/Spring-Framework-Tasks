package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.Teacher;



public class Main {

	public static void main(String[] args) {
	
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Teacher.class);
		
	
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		
		Session session = sessionFactory.getCurrentSession();
	
		Transaction transaction = session.beginTransaction();
		
		Teacher teacher = new Teacher("Ahmrd" , 25 , "Fayoum");
		session.save(teacher);
		
		transaction.commit();
		session.close();
		sessionFactory.close();
		
		

	}

}



