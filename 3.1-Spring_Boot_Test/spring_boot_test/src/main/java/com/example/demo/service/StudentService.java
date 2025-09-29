package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentDto;

import jakarta.transaction.SystemException;

public interface StudentService {
	
	StudentDto getStudentByID(Long id) throws SystemException;
	
	List<StudentDto> getStudents();
	
	StudentDto saveStudent(StudentDto studentDto) throws SystemException;
	
	StudentDto updateStudent(StudentDto studentDto) throws SystemException;
	
	void deleteStudentById(Long id) throws SystemException;
	

}
