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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;

import jakarta.transaction.SystemException;

@RestController
@RequestMapping("course")
public class CourseController {
	
	private final CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}
	
	//-----------------------------------//
	@GetMapping()
	public List<Course> getAllEntites()
	{
		return courseService.getCourses();
	}
	
	@GetMapping("/{id}")
	public Course getEntityById(@PathVariable Long id)
	{
		return courseService.getCourseById(id);
	}
	
	@PostMapping("/addCourse")
	public Course addEntity(@RequestBody Course course) throws SystemException
	{
		return courseService.addCourse(course);
	}
	
	@PutMapping("/update")
    public Course updateEntity(@RequestBody Course course) throws SystemException {

        return courseService.updateCourse(course);
    }
	
	
	@DeleteMapping("/{id}")
	public void deleteEntity(@PathVariable Long id) throws SystemException {
	    courseService.deleteCourseById(id);

	}
//	
//	@PutMapping("/addCourseToStudent")
//    public Student registerStudentToCourse(@RequestParam Long studentId, @RequestParam Set<Long> courseIds) throws SystemException {
//
//        return studentService.registerStudentToCourse(studentId, courseIds);
//    }

}
