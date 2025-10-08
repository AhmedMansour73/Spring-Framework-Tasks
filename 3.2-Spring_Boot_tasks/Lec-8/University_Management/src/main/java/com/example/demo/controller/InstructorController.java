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

import com.example.demo.model.Instructor;
import com.example.demo.service.InstructorService;

import jakarta.transaction.SystemException;

@RestController
@RequestMapping("instructor")
public class InstructorController {
	
	private final InstructorService instructorService;

	@Autowired
	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}
	
	//-----------------------------------//
	@GetMapping()
	public List<Instructor> getAllEntites()
	{
		return instructorService.getInstructors();
	}
	
	@GetMapping("/{id}")
	public Instructor getEntityById(@PathVariable Long id)
	{
		return instructorService.getInstructorById(id);
	}
	
	@PostMapping("/addInstructor")
	public Instructor addEntity(@RequestBody Instructor instructor) throws SystemException
	{
		return instructorService.addInstructor(instructor);
	}
	
	@PutMapping("/update")
    public Instructor updateEntity(@RequestBody Instructor instructor) throws SystemException {

        return instructorService.updateInstructor(instructor);
    }
	
	
	@DeleteMapping("/{id}")
	public void deleteEntity(@PathVariable Long id) throws SystemException {
		instructorService.deleteInstructorById(id);

	}


}
