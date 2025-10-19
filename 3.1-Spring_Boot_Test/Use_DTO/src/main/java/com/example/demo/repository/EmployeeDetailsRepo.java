package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Long> {

}
