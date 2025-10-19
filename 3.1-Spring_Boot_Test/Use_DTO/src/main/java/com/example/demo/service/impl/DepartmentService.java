package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmployeeRepo;

import jakarta.transaction.SystemException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
	
	private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
	
	public Department getDepartmentById(Long id)
	{
		return departmentRepo.findById(id).get();
	}
	
	public List<Department> getDepartment()
	{
		return departmentRepo.findAll();
	}
	
	public Department addDepartment(Department department) throws SystemException
	{
		if(Objects.nonNull(department.getId()))
		{
			throw new SystemException("Id must be null to add");
		}
		
	    return departmentRepo.save(department);
	}

	

}

