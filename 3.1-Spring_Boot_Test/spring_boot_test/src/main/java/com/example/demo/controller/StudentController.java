package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;

import jakarta.transaction.SystemException;

@RestController
@RequestMapping("student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping()
	public List<StudentDto> getAllEintities() {
		return studentService.getStudents();
	}
	@GetMapping("/{id}")
	public StudentDto getEintitiesById(@PathVariable Long id) throws SystemException {
		return studentService.getStudentByID(id);
	}
	
	@PostMapping("/add")
	public StudentDto addEntity(@RequestBody StudentDto studentDto) throws SystemException
	{
		return studentService.saveStudent(studentDto);
	}
	
	
	@PutMapping("/update")
	public StudentDto updateEntity(@RequestBody StudentDto studentDto) throws SystemException
	{
		return studentService.updateStudent(studentDto);
	}
	
	@DeleteMapping("/delete")
	public void deleteEntity(@RequestParam Long id) throws SystemException
	{
		studentService.deleteStudentById(id);
	}
}
