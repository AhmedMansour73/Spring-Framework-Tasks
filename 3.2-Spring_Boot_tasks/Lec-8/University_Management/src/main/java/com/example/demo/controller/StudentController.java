package com.example.demo.controller;

import java.util.List;
import java.util.Set;

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

import com.example.demo.model.Student;
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
	
	//-----------------------------------//
	@GetMapping()
	public List<Student> getAllEntites()
	{
		return studentService.getStudents();
	}
	
	@GetMapping("/{id}")
	public Student getEntityById(@PathVariable Long id)
	{
		return studentService.getStudentById(id);
	}
	
	@PostMapping("/addStudent")
	public Student addEntity(@RequestBody Student student) throws SystemException
	{
		return studentService.addStudent(student);
	}
	
	@PutMapping("/update")
    public Student updateEntity(@RequestBody Student student) throws SystemException {

        return studentService.updateStudent(student);
    }
	
	@PutMapping("/addCourseToStudent")
    public Student registerStudentToCourse(@RequestParam Long studentId, @RequestParam Set<Long> courseIds) throws SystemException {

        return studentService.registerStudentToCourse(studentId, courseIds);
    }

	@DeleteMapping("/{id}")
	public void deletEntity(@PathVariable Long id) throws SystemException
	{
		studentService.deleteSٍtudentById(id);
	}
}
