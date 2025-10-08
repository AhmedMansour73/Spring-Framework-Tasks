package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Course;

import jakarta.transaction.SystemException;


public interface CourseService {
	
// Assign an instructor to a course
	Course addCourse(Course course) throws SystemException;
// Assign an instructor to a course
	Course updateCourse(Course course) throws SystemException;
	
	List<Course> getCourses();
	
	Course getCourseById(Long id);
	
	void deleteCourseById(Long id) throws SystemException;
	
	

}
