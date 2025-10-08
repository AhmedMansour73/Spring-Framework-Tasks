package com.example.demo.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.InstructorRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.CourseService;

import jakarta.transaction.SystemException;

@Service
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepo courseRepo;
	private final InstructorRepo instructorRepo;
	private final StudentRepo studentRepo;

	@Autowired
	public CourseServiceImpl(CourseRepo courseRepo , InstructorRepo instructorRepo, StudentRepo studentRepo) {
		super();
		this.courseRepo = courseRepo;
		this.instructorRepo = instructorRepo;
		this.studentRepo = studentRepo;
	}

	@Override
	public Course addCourse(Course course) throws SystemException {
		Instructor instructor = course.getInstructor();

        if (instructor == null) {
            throw new SystemException("Instructor is required for the course");
        }

        if (instructor.getId() != null) {
            Instructor existingInstructor = instructorRepo.findById(instructor.getId())
                    .orElseThrow(() -> new SystemException("Instructor not found with ID: " + instructor.getId()));

            course.setInstructor(existingInstructor);
        }
           
        return courseRepo.save(course); 
	}

	@Override
	public Course updateCourse(Course course) throws SystemException {

	    if (course.getId() == null) {
	        throw new SystemException("Course ID must not be null for update");
	    }

	    Course existingCourse = courseRepo.findById(course.getId())
	            .orElseThrow(() -> new SystemException("Course not found"));

	    if (course.getTitle() != null)
	        existingCourse.setTitle(course.getTitle());

	    if (course.getDescription() != null)
	        existingCourse.setDescription(course.getDescription());

	    if (course.getInstructor() != null) {
	        Instructor instructor = course.getInstructor();

	        if (instructor.getId() != null) {
	            Instructor existingInstructor = instructorRepo.findById(instructor.getId())
	                    .orElseThrow(() -> new SystemException("Instructor not found with ID: " + instructor.getId()));
	            existingCourse.setInstructor(existingInstructor);
	        } else {
	            // new Instructor 
	            existingCourse.setInstructor(instructor);
	        }
	    }

	    //
	    if (course.getStudents() != null) {
	        Set<Student> updatedStudents = new HashSet<>();
	        for (Student s : course.getStudents()) {
	            if (s.getId() != null) {
	                Student existingStudent = studentRepo.findById(s.getId())
	                        .orElseThrow(() -> new SystemException("Student not found with ID: " + s.getId()));
	                updatedStudents.add(existingStudent);
	            } else {
	                //
	            	updatedStudents.add(s);
	            }
	        }
	        existingCourse.setStudents(updatedStudents);
	    }

	    return courseRepo.save(existingCourse);
	}
	

	@Override
	public List<Course> getCourses() {
		
		return courseRepo.findAll();
	}

	@Override
	public Course getCourseById(Long id) {
		
		return courseRepo.findById(id).get();
	}

	@Override
	public void deleteCourseById(Long id) throws SystemException {
		Course course = courseRepo.findById(id)
	            .orElseThrow(() -> new SystemException("Course not found with ID: " + id));

		if (course.getStudents() != null) {
	        for (Student student : course.getStudents()) {
	            student.getCourses().remove(course);
	        }
	        course.getStudents().clear();
	    }

	    
	    courseRepo.delete(course);
		
	}

}
