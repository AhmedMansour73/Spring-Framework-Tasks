package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Instructor;

import jakarta.transaction.SystemException;

public interface InstructorService {
	
	
	Instructor addInstructor(Instructor instructor) throws SystemException;

	Instructor updateInstructor(Instructor instructor) throws SystemException;
		
	List<Instructor> getInstructors();
	
//	Get courses taught by an instructor
	Instructor getInstructorById(Long id);
	
	void deleteInstructorById(Long id) throws SystemException;

	
}
