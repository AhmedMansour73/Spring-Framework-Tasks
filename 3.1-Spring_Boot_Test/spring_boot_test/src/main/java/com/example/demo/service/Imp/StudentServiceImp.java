package com.example.demo.service.Imp;

import java.text.Collator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import io.micrometer.common.lang.NonNull;
import jakarta.transaction.SystemException;

@Service
public class StudentServiceImp implements StudentService {
	
	private final StudentRepository studentRepository;
	
	
    @Autowired
	public StudentServiceImp(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

    
    
	@Override
	public StudentDto getStudentByID(Long id) throws SystemException {
		Optional<Student> studentOptional = studentRepository.findById(id);
		
		if(studentOptional.isEmpty())
		{
			throw new SystemException("Not have entit by this id: "+ id);
		}
		
		Student student = studentOptional.get();
		
		return new StudentDto(
				student.getId(),
				student.getName(),
				student.getPhoneNumber(),
				student.getUserName(),
				student.getPassword()	); 
	}

	@Override
	public List<StudentDto> getStudents() {
		List<Student> students = studentRepository.findAll();
		return students.stream().map( student -> new StudentDto(
				student.getId(),
				student.getName(),
				student.getPhoneNumber(),
				student.getUserName(),
				student.getPassword()	)
				).collect(Collectors.toList());
		
		 
	}

	@Override
	public StudentDto saveStudent(StudentDto studentDto) throws SystemException {
		
		if(Objects.nonNull(studentDto.getId()))
		{
			throw new SystemException("Id must be null to add");
		}
		if(Objects.isNull(studentDto.getName()))
		{
			throw new SystemException("Name must be NOT null to add");
		}
		if(Objects.isNull(studentDto.getPhoneNumber()))
		{
			throw new SystemException("PhoneNumber must be NOT null to add");
		}
		Student student = new Student(
				studentDto.getName(),
				studentDto.getPhoneNumber(),
				studentDto.getUserName(),
				studentDto.getPassword());
		
		student = studentRepository.save(student);
		studentDto.setId(student.getId());
		return studentDto;
	}

	@Override
	public StudentDto updateStudent(StudentDto studentDto) throws SystemException {
		if(Objects.isNull(studentDto.getId()))
		{
			throw new SystemException("Id must be NOT NULL to Update");
		}
		
		Student student = new Student(
				studentDto.getId(),
				studentDto.getName(),
				studentDto.getPhoneNumber(),
				studentDto.getUserName(),
				studentDto.getPassword());
		student = studentRepository.save(student);
//		studentDto.setId(student.getId());
		return studentDto;
	}

	@Override
	public void deleteStudentById(Long id) throws SystemException {
		Optional<Student> student = studentRepository.findById(id);
		
		if(student.isEmpty())
		{
			throw new SystemException("Not have entit by this id: "+ id);
		}
		studentRepository.deleteById(id);
		
	}

}
