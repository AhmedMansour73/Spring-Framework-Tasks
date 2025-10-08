package com.example.demo.service.Impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;

import jakarta.transaction.SystemException;
import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepo studentRepo;
	private final CourseRepo courseRepo;
	
	@Autowired
	public StudentServiceImpl(StudentRepo studentRepo ,CourseRepo courseRepo) {
		super();
		this.studentRepo = studentRepo;
		this.courseRepo = courseRepo;
	}
	
	//--------------------------------//
	@Override
	public Student addStudent(Student student) throws SystemException {
		
		if(Objects.nonNull(student.getId()))
		{
			throw new SystemException("Id must be null to add");
		}
		if(student.getName().trim().isEmpty())
		{
			throw new SystemException("Name must be NOT null to add");
		}
		if(student.getEmail().trim().isEmpty())
		{
			throw new SystemException("Email must be NOT null to add");
		}
		
		 if (student.getCourses() != null && !student.getCourses().isEmpty()) {
		        throw new SystemException("Courses must be empty when adding a new student");
		    }
		return studentRepo.save(student);
	}

	@Override
	public Student updateStudent(Student student) throws SystemException {
		if (student.getId() == null) {
	        throw new SystemException("Student ID must not be null for update");
	    }

		 Student existingStudent = studentRepo.findById(student.getId())
		            .orElseThrow(() -> new SystemException("Student not found"));

		    existingStudent.setName(student.getName());
		    existingStudent.setEmail(student.getEmail());
		    existingStudent.setCourses(student.getCourses()); 
		    
	    return studentRepo.save(existingStudent);
	}

	@Override
	public List<Student> getStudents() {
		
		return studentRepo.findAll();
	}

	@Override
	public Student getStudentById(Long id) {
		
		return studentRepo.findById(id).get();
	}

	@Override
	public Student registerStudentToCourse(Long studentId, Set<Long> courseIds) throws SystemException {
		Student student = studentRepo.findById(studentId)
	            .orElseThrow(() -> new SystemException("Student not found"));

	    Set<Course> courses = new HashSet<>(courseRepo.findAllById(courseIds));

	    if (courses.isEmpty()) {
	        throw new SystemException("No valid courses found to assign");
	    }

//	    student.setCourses(courses);  // remove old courses
	    student.getCourses().addAll(courses);
	    return studentRepo.save(student);
		
	}

	@Override
	public void registerStudentWithNewCourse(Long studentId, Course newCourse) {
		// TODO Auto-generated method stub
		
	}


	@Override
	@Transactional
	public void deleteSٍtudentById(Long id) throws SystemException {
		Student student = studentRepo.findById(id)
	            .orElseThrow(() -> new SystemException("Student not found with ID: " + id));

	    if (student.getCourses() != null && !student.getCourses().isEmpty()) {
	        for (Course course : student.getCourses()) {
	            course.getStudents().remove(student); // remove student from each course
	        }
	        courseRepo.saveAll(student.getCourses()); // نحفظ التغييرات
	    }

	    // 3️⃣ نحذف الطالب نفسه
	    studentRepo.delete(student);	}
	
	
}
