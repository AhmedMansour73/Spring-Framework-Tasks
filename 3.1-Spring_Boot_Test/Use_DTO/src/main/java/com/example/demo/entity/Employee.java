package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Employee_DTO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private Double salary;


    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeDetails details;

// CascadeType.PERSIST ,CascadeType.MERGE
    @ManyToMany
    @JoinTable(
        name = "employee_department_DTO",
        joinColumns = @JoinColumn(name = "employee_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "department_id", nullable = false),
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"employee_id", "department_id"})
            }
    )
    
    private Set<Department> departments;

    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Email> emails;

    
}