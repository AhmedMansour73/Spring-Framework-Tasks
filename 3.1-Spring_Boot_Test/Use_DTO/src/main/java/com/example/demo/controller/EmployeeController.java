package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.impl.EmployeeService;

import jakarta.transaction.SystemException;

@RestController
@RequestMapping("emplpyee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping()
	public List<Employee> getEntities(){
		return employeeService.getEmployees();
	}
	
	@GetMapping("/id")
	public Employee getEntitiesByID(@RequestParam Long id){
		return employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/add")
	public Employee addEntity(@RequestBody Employee empl) throws SystemException
	{
		return employeeService.addEmployee(empl);
	}
	
	@PostMapping("/addEmpAndDept")
	public ResponseEntity<Employee> addEmployee( @RequestBody Employee employee, @RequestParam Set<Long> departmentIds) throws SystemException 
	{ 
		return ResponseEntity.ok(employeeService.addEmployee(employee, departmentIds)); 
	}
	
	@PutMapping("/update")
	public Employee updateEntity(@RequestBody Employee empl) throws SystemException
	{
		return employeeService.updateEmployee(empl);
	}
	
	@DeleteMapping("/delete")
	public void deleteEntityById(@RequestParam Long employeeId) throws SystemException
	{
		employeeService.deleteEmployeeById(employeeId);
	}
	
}
