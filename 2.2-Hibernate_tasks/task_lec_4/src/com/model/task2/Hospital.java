package com.model.task2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "hospital_id")
    private Long id;

    @Column(nullable = false, length = 100) 
    private String name;

    @Column(nullable = false) 
    private int numberOfDoctors;

    @Column(nullable = false) 
    private int numberOfPatient;
    
    @OneToMany(mappedBy = "hospitailDoctor")
    private List<Doctor> doctors;

    @ManyToMany
    @JoinTable(
    		name="hosp_patient",
    		joinColumns = @JoinColumn(name ="hosp_id"),
    		inverseJoinColumns = @JoinColumn(name ="patient_id")
    		)
    private List<Patient> patintHosp;
    // Getters & Setters
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

    public int getNumberOfDoctors() {
        return numberOfDoctors;
    }
    public void setNumberOfDoctors(int numberOfDoctors) {
        this.numberOfDoctors = numberOfDoctors;
    }

    public int getNumberOfPatient() {
        return numberOfPatient;
    }
    public void setNumberOfPatient(int numberOfPatient) {
        this.numberOfPatient = numberOfPatient;
    }
}