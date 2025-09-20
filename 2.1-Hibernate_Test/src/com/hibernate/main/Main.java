package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Email;
import com.hibernate.model.Student;
import com.hibernate.model.StudentDetails;

public class Main {

	public static void main(String[] args) {
	
		/*
		 * we create a new object from the Configuration class in Hibernate.
		 * This object is responsible for reading Hibernate settings (driver, URL, username/password, properties like hbm2ddl.auto, etc.).
		 * It also contains the definition of Entities (classes with @Entity annotations).
		 *  3 ways to know Entities 
		 *  1- Definition in hibernate.cfg.xml file --> file configuration like this
		 *  	<mapping class="com.example.model.Item"/>
		 *  2-Definition in code (Programmatically)
		 *  	configuration.addAnnotatedClass(Item.class);
		 *  
		 *  3- In some frameworks (such as Spring Boot)
		 *  	It scans locations containing @Entity (based on @EntityScan or @SpringBootApplication).
		 *  	Therefore, you don't need to manually map.
		 *  	
		 */
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(StudentDetails.class)
				.addAnnotatedClass(Email.class);
		
		/*
		 * The SessionFactory is the "factory" that generates your Sessions.
		 * It takes the settings in your Configuration (such as hibernate.cfg.xml and Entities) and creates a heavy object.
		 * This is created once during the lifetime of the application and is used as long as the program is running.
		 * It contains:
		 * 	Database connection information.
		 * 	The Connection Pool.
		 * 	Definitions for all Entities.
		 * 	The code that manages Sessions.
		 */
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		/*
		 * Session in Hibernate = An interface that manages interaction with the database.
		 * You can use it to:
		 * 	save(), update(), delete(), get(), createQuery(), etc.
		 * 	It's like a "window" between your code and the database.
		 */
		
		Session session = sessionFactory.getCurrentSession();
		
		/*
		 * Each session in Hibernate is associated with a transaction. 
		 * The transaction here is an object of type org.hibernate.Transaction.
		 * It represents the operation that will be executed as a single unit:
		 * Either it all succeeds (Commit)
		 * Either it all rolls back (Rollback).
		 */
		Transaction transaction = session.beginTransaction();

		
		
		/*
		 * here why make update without using session.update();
		 * 		==> Important Concept: Entity States in Hibernate
		 * --> Persistent (Managed)
		 * 	An object stored in the DB and associated with a session.
		 * 	Any modifications to it are monitored by Hibernate (Automatic Dirty Checking).
		 * 		Automatic Dirty Checking 
		 * 			Hibernate does something called Dirty Checking.	
		 * 			This means that at the end of the transaction, or at session.flush(), 
		 * 				Hibernate compares the old version with the current version.
		 * 			If it detects a change, it generates an SQL UPDATE.
		 * 
		 * --> Detached
		 *   An object is stored in the DB, but the session was closed or terminated.
		 *   Hibernate is not tracking it at this time.
		 */
		
		Student student3 = session.get(Student.class, 5);
		System.out.println("Before update --> "+student3.toString());
		
		session.detach(student3); // raises (removes) control from ORM until use session.update();
		student3.setName("name update111111");
		
		System.out.println("appera here not DB"+student3.toString());
		
		/*
		 * save changes on DB
		 */
		transaction.commit();
		
		session.close();
		sessionFactory.close();
		
		

	}

}



//Student student2 = new Student("Ahmed",24,"fayoum");
/*
 * generate SQL query to insert data
 */
//session.save(student2);

/*
 * to select student by id

Student student1 = session.get(Student.class, 1);
System.out.println("Before update --> "+student1.toString()); */

/*
 * update
 
student1.setName("Ali");
session.update(student1);
System.out.println("After update --> "+student1);*/


/*
 * Delete
 
if (student1 != null) {
    session.delete(student1); 
}*/
