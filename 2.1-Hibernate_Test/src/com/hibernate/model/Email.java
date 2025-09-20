package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="email_id")
	private int id;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	@ManyToOne()
	@JoinColumn(name ="std_id" , nullable=false)
	private Student studentEmail;
}
