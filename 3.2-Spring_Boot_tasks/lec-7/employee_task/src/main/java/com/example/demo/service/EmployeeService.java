package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

import jakarta.transaction.SystemException;

public interface EmployeeService {
	
	// api to get all employee
	List<Employee> getEmployees();
	
	// api to get all employee by List of ids
	List<Employee> getEmployeesByIds(List<Long> ids);
	
	// api to save employee
	Employee addEmployee(Employee empl) throws SystemException;
	
	// api to save List of employee
	List<Employee> addListEmployee(List<Employee> empls) throws SystemException;
	
	// api to update employee
	Employee updateEmployee(Employee empl) throws SystemException;
	
	// api to update List of employee
	List<Employee> updateListListEmployee(List<Employee> empls) throws SystemException;
	
	// api to delete all employee
	void removeAllEmployees();

	// api to delete employee By ID
	void removeEmployeebyId(Long id) throws SystemException;
	
	// api to delete employee By List of ID
	void removeEmployeesbyIds(List<Long> ids);
	
}
