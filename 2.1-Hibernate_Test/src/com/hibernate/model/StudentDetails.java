package com.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class StudentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="details_id")
	private int id;
	
	@Column(unique= true)
	private String userName;
	
	private String password;
	
	@OneToOne
	@JoinColumn(name ="std_id" , unique=true , nullable=false)
	private Student student;
	
	
	

}
