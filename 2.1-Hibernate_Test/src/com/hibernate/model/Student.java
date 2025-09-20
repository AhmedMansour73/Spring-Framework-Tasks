package com.hibernate.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="student_id")
	private int id;
	
	@Column( name="student_name" , nullable =false)
	private String name;
	
	/*
	 * age is not null because use primitive type (int)
	 * 		The value null is not accepted in Java.
	 *		int age = null; // Error
	 * if you want use null by sing Integer (wrapper type)
	 * 		Integer age = null; // Okay
	 */
	private int age;
	private String address;
	
	@OneToOne(mappedBy ="student")
	private StudentDetails studentDetails;
	
	@OneToMany(mappedBy = "studentEmail")
	private List<Email> email;
	
	@ManyToMany(mappedBy = "studentCourse")
	private List<Course> course;
	
	public Student() {}
	
	public Student(String name, int age, String address) {
	
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Student(int id, String name, int age, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	// Getter and Setter
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
	
	

}
