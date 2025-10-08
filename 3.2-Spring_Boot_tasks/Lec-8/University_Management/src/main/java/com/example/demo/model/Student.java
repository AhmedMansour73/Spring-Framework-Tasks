package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Student_task8")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment ID
    private Long id;

    @Column(nullable = false)  
    private String name;

    @Column(unique = true, nullable = false) 
    private String email;
    
 // Many-to-Many relation with Course
    @ManyToMany
    @JoinTable(
        name = "student_course_task8", 
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "course_id") 
    )
    private Set<Course> courses = new HashSet<>();
    
 // --- Getters & Setters ---

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

    
    
    
    
}