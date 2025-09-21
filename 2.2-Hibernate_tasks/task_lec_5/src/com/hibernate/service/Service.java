package com.hibernate.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Friends;
import com.hibernate.model.Post;
import com.hibernate.model.User;
import com.hibernate.model.UserDetails;

public class Service {
	private static Session session ;
	
	public static void useCascade() {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Friends.class)
				.addAnnotatedClass(UserDetails.class)
				.addAnnotatedClass(Post.class);
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		/*
		 * 
		 */
		// task 3  with using cascade
		
		saveData();	
		
		
		/*
		 * 
		 */
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	private static void saveData() {
		
		User user1 = new User("Mhammed" , 30);
		UserDetails details1 = new UserDetails("cairo" ,"0120000000");
		Friends frind1 = new Friends("Hasssan"); 
		Friends frind2 = new Friends("Ahmed");
		Post post1 = new Post("HHH1","bla bla bla");
		Post post2 = new Post("HHH2","as as as");
	
		
		//  ( OneToOne )
		details1.setUser(user1);
		user1.setUserDetails(details1);
		
		
		// (OneToMAny)
		user1.setPosts(List.of(post1, post2));
		post1.setUser(user1);
		post2.setUser(user1);
		
		
		// ( ManyToMany )
		
		user1.setFriends(List.of(frind1, frind2));
		frind1.setUsers(List.of(user1));
		frind2.setUsers(List.of(user1));
		
		session.persist(user1);
		
	} 
	
	private static void updateData(Session session) {

	    User user1 = session.get(User.class, 1L); 
	    
	    user1.setName("Mohammed Updated");
	    user1.setAge(35);

	    // (OneToOne)
	    UserDetails details1 = user1.getUserDetails();
	    details1.setAddress("Alexandria");
	    details1.setPhone("0111111111");

	    // (OneToMany)
	    Post post1 = user1.getPosts().get(0);
	    post1.setHeader("Updated Header");
	    post1.setContent("Updated Content");

	    Post post3 = new Post("New Post", "new content");
	    post3.setUser(user1);
	    user1.getPosts().add(post3);

	    // (ManyToMany)
	    Friends newFriend = new Friends("Omar");
	    user1.getFriends().add(newFriend);
	    newFriend.getUsers().add(user1);

	    session.merge(user1); 
	}
	
	private static void getUserDetails(Session session, Long userId) {

	    User user = session.get(User.class, userId);  

	    if (user == null) {
	        System.out.println("User not found with id: " + userId);
	        return;
	    }

	    // --- User Info ---
	    System.out.println("User: " + user.getName() + ", Age: " + user.getAge());

	    // --- OneToOne: UserDetails ---
	    UserDetails details = user.getUserDetails();
	    if (details != null) {
	        System.out.println("Details: " + details.getAddress() + ", " + details.getPhone());
	    } else {
	        System.out.println("No details found for this user.");
	    }

	    // --- OneToMany: Posts ---
	    if (user.getPosts() != null && !user.getPosts().isEmpty()) {
	        System.out.println("Posts:");
	        for (Post p : user.getPosts()) {
	            System.out.println("   - " + p.getHeader() + ": " + p.getContent());
	        }
	    } else {
	        System.out.println("No posts found for this user.");
	    }

	    // --- ManyToMany: Friends ---
	    if (user.getFriends() != null && !user.getFriends().isEmpty()) {
	        System.out.println("Friends:");
	        for (Friends f : user.getFriends()) {
	            System.out.println("   - " + f.getName());
	        }
	    } else {
	        System.out.println("No friends found for this user.");
	    }
	}



}
