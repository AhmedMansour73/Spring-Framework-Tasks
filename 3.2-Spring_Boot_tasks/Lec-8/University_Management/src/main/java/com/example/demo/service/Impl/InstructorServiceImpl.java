package com.example.demo.service.Impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.InstructorRepo;
import com.example.demo.service.InstructorService;

import jakarta.transaction.SystemException;


@Service
public class InstructorServiceImpl implements InstructorService{

	private final CourseRepo courseRepo;
	private final InstructorRepo instructorRepo;


	@Autowired
	public InstructorServiceImpl(CourseRepo courseRepo , InstructorRepo instructorRepo) {
		super();
		this.courseRepo = courseRepo;
		this.instructorRepo = instructorRepo;

	}


	@Override
	public Instructor addInstructor(Instructor instructor) throws SystemException {
		
		if (instructor.getCourses() != null && !instructor.getCourses().isEmpty()) {
            throw new SystemException("Instructor must be added without courses");
        }

		if (instructor.getName() == null || instructor.getName().isEmpty()) {
            throw new SystemException("Instructor name is required");
        }
        if (instructor.getEmail() == null || instructor.getEmail().isEmpty()) {
            throw new SystemException("Instructor email is required");
        }

         return instructorRepo.save(instructor);
	}


	@Override
	public Instructor updateInstructor(Instructor instructor) throws SystemException {
		
		 if (instructor.getId() == null) {
		        throw new SystemException("Instructor ID must not be null for update");
		    }

		    Instructor existingInstructor = instructorRepo.findById(instructor.getId())
		            .orElseThrow(() -> new SystemException("Instructor not found with ID: " + instructor.getId()));

		    if (instructor.getName() != null && !instructor.getName().isEmpty()) {
		        existingInstructor.setName(instructor.getName());
		    }
		    if (instructor.getEmail() != null && !instructor.getEmail().isEmpty()) {
		        existingInstructor.setEmail(instructor.getEmail());
		    }

		    if (instructor.getCourses() != null && !instructor.getCourses().isEmpty()) {
		        throw new SystemException("Cannot update courses from instructor update");
		    }

		   return instructorRepo.save(existingInstructor);
	}


	@Override
	public List<Instructor> getInstructors() {
	
		return instructorRepo.findAll();
	}


	@Override
	public Instructor getInstructorById(Long id) {
		
		return instructorRepo.findById(id).get();
	}


	@Override
	public void deleteInstructorById(Long id) throws SystemException {
		Instructor instructor = instructorRepo.findById(id)
	            .orElseThrow(() -> new SystemException("Instructor not found with ID: " + id));

	    Instructor defaultInstructor = instructorRepo.findByEmail("unassigned@system.com")
	            .orElseGet(() -> {
	                Instructor newInstructor = new Instructor();
	                newInstructor.setName("Unassigned Instructor");
	                newInstructor.setEmail("unassigned@system.com");
	                return instructorRepo.save(newInstructor);
	            });

	    Set<Course> courses = instructor.getCourses();
	    if (courses != null && !courses.isEmpty()) {
	        for (Course course : courses) {
	            course.setInstructor(defaultInstructor);
	        }
	    }

	   instructorRepo.delete(instructor);
	}
}
