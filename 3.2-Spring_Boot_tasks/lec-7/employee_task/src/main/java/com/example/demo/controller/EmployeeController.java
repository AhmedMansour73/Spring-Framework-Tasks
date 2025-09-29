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

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.transaction.SystemException;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	
	// ---------- GET ----------
	// Get All Employees 
    @GetMapping()
    public List<Employee> getEmployeesByIds() {
        return employeeService.getEmployees();
    }
    
    // Get Employees by list of IDs
    @GetMapping("/by-ids")
    public List<Employee> getEmployeesByIds(@RequestParam List<Long> ids) {
        return employeeService.getEmployeesByIds(ids);
    }

    // ---------- POST ----------
    // Add one employee
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) throws SystemException {
        return employeeService.addEmployee(employee);
    }

    // Add list of employees
    @PostMapping("/batch")
    public List<Employee> addListEmployee(@RequestBody List<Employee> employees) throws SystemException {
        return employeeService.addListEmployee(employees);
    }

    // ---------- PUT ----------
    // Update one employee
    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) throws SystemException {
        return employeeService.updateEmployee(employee);
    }

    // Update list of employees
    @PutMapping("/batch")
    public List<Employee> updateListEmployee(@RequestBody List<Employee> employees) throws SystemException {
        return employeeService.updateListListEmployee(employees);
    }

    // ---------- DELETE ----------
    // Delete ALL employees
    @DeleteMapping("/delete")
    public void removeAllEmployees() {
        employeeService.removeAllEmployees();
    }

    // Delete employee by ID
    @DeleteMapping("/delete/{id}")
    public void removeEmployeeById(@PathVariable Long id) throws SystemException {
        employeeService.removeEmployeebyId(id);
    }

    // Delete list of employees by IDs
    @DeleteMapping("delete/by-ids")
    public void removeEmployeesByIds(@RequestParam List<Long> ids) {
        employeeService.removeEmployeesbyIds(ids);
    }
	
	

}
