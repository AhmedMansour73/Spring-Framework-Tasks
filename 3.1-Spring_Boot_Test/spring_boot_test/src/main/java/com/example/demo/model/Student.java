package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="Student_Test")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(unique = true ,nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	// getter and Setter

	public Student() {	}
	
	public Student(Long id, String name, String phoneNumber, String userName, String password) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
	}
	
	public Student( String name, String phoneNumber, String userName, String password) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.userName = userName;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
