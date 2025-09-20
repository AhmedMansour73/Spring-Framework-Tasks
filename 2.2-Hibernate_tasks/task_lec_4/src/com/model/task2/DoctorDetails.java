package com.model.task2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity

public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "details_id")
    private Long id;

    @Column(nullable = false, length = 200) 
    private String fullAddress;

    @Column(nullable = false, length = 50) 
    private String firstName;

    @Column(nullable = false, length = 50) 
    private String lastName;

    @Column(nullable = false) 
    private int age;
    
    @OneToOne
    @JoinColumn(name="doc_id" , unique=true ,nullable=false)
    private Doctor doctor;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}