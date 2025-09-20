package com.model.task2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "doctor_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String userName;

    @Column(nullable = false) 
    private Double salary;
    
    @OneToOne(mappedBy = "doctor")
    private DoctorDetails doctorDetails;
    
    @ManyToOne()
    @JoinColumn(name="hospitail_id" ,nullable=false)
    private Hospital hospitailDoctor;
    
    @OneToMany(mappedBy = "doctorForPatient")
    private List<Patient> patients;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
