package com.hibernate.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.*;
import com.hibernate.service.Service;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Friends.class)
				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(Post.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		/*
		 * 
		 */
		// task 2  without using cascade
		
		User user1 = new User("Ahmed" , 25);
		UserDetails details1 = new UserDetails("fayoum" ,"0100000000");
		Friends frind1 = new Friends("Ali"); 
		Friends frind2 = new Friends("Islam");
		Post post1 = new Post("h1","bla bla bla");
		Post post2 = new Post("h2","as as as");
		/*
		 * not run because not-null property references a null or transient value 
		 */
//		session.save(user1);
//		session.save(details1);
//		session.save(frind1);
//		session.save(frind2); 
//		session.save(post1);
//		session.save(post2);
		
		/*
		 * solution 
		 * must be save (dependent , inverse side) before (followed , owning side)
		 */
		
		
		// add user(dependent) in first the add details(followed) for this user ( OneToOne )
		details1.setUser(user1);
		
		session.save(user1);
		session.save(details1);
		
		// add user(dependent) in first the add posts(followed) for this user (OneToMAny)
		post1.setUser(user1);
		post2.setUser(user1);
		session.save(post1);
		session.save(post2);
		
		
		// ( ManyToMany )
		/*
		 * if use user1.setFriends(List.of(frind1, frind2)); only 
		 * join table will update, but if you later read frind1.getUsers() inside the same transaction,
		 *  you'll find it empty (because you didn't set it from the other side).
		 *  If you want to keep the entire object model synchronous (in Java memory itself), it's best to add both:
		 */
		user1.setFriends(List.of(frind1, frind2));
		frind1.setUsers(List.of(user1));
		frind2.setUsers(List.of(user1));
		
		session.save(frind1);
		session.save(frind2); 		
		
		
		/*
		 * 
		 */
		transaction.commit();
		session.close();
		sessionFactory.close();
		
		
		/*
		 * using cascade 
		 */
		
		Service.useCascade();
		
	}

}
