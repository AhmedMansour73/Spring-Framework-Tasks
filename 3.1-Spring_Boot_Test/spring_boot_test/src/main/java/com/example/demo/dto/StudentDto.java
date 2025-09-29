package com.example.demo.dto;


public class StudentDto {
	
	private Long id;
	
	private String name; 
	
	private String phoneNumber;
	
	private String userName;
	
	private String password;
	
	// getter and Setter
	
	public StudentDto() {	}
	
	public StudentDto(Long id, String name, String phoneNumber, String userName, String password) {
		this.id = id;
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
