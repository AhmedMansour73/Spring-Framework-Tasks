package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.model.task1.Player;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Player.class);
		
	
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		
		Session session = sessionFactory.getCurrentSession();
	
		Transaction transaction = session.beginTransaction();
		
		Player player = new Player("Ahmrd" , "25" , true);
		
//		save object
		session.save(player);
		
//		to update and delete must get object
		Player playerForUpdate = session.get(Player.class, 1);
		
//		update
		playerForUpdate.setName("Ali");
		session.update(playerForUpdate);
		
//		delete
		if (playerForUpdate != null) {
		    session.delete(playerForUpdate); 
		}
		
		transaction.commit();
		session.close();
		sessionFactory.close();

	}

}
