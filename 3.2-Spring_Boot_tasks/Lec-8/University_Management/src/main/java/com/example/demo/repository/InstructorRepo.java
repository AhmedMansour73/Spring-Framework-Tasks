package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor , Long> {

	Optional<Instructor> findByEmail(String email);
}
