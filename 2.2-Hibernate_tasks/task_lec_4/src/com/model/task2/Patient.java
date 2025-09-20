package com.model.task2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "patient_id")
    private Long id;

    @Column(nullable = false, length = 100) 
    private String name;

    @Column(nullable = false, length = 100) 
    private String typeOfDisease;
    
    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Doctor doctorForPatient;
    
    @ManyToMany(mappedBy = "patintHosp")
    private List<Hospital> hospitals;

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

    public String getTypeOfDisease() {
        return typeOfDisease;
    }
    public void setTypeOfDisease(String typeOfDisease) {
        this.typeOfDisease = typeOfDisease;
    }
}
