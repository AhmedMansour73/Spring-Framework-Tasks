package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="EmplDetails_DTO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private Double benefitPercentage; 
    
    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false,unique = true)
    @JsonIgnore
    private Employee employee;
}
