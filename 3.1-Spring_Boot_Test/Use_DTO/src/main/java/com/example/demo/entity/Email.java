package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Email_DTO")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(unique = true ,nullable = false)
    private String email;
	
	@Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "employee_id" , nullable = false)
    @JsonIgnore
    private Employee employee;

}
