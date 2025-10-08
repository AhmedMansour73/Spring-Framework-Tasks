package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.model.Course;
import com.example.demo.model.Student;

import jakarta.transaction.SystemException;

public interface StudentService {
	
	Student addStudent(Student student) throws SystemException;
	
	Student updateStudent(Student student) throws SystemException;
	
	List<Student> getStudents();
	
	Student getStudentById(Long id);
	
	// if course is exists
	Student registerStudentToCourse(Long studentId, Set<Long> courseId) throws SystemException;
	
	// if course NOT exists
	void registerStudentWithNewCourse(Long studentId, Course newCourse);
//	
//	Student addStudentWithCourse(Student student);
	
	void deleteSٍtudentById(Long id) throws SystemException;

}
