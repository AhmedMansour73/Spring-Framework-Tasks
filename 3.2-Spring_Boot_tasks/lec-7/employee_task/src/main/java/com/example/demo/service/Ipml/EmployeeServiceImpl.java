package com.example.demo.service.Ipml;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import jakarta.transaction.SystemException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getEmployeesByIds(List<Long> ids) {
		
		return employeeRepository.findAllById(ids);
	}

	@Override
	public Employee addEmployee(Employee empl) throws SystemException {
		if(Objects.nonNull(empl.getId()))
		{
			throw new SystemException("Id must be null to add");
		}
		if(Objects.isNull(empl.getName()))
		{
			throw new SystemException("Name must be NOT null to add");
		}
		return employeeRepository.save(empl);
	}

	@Override
	public List<Employee> addListEmployee(List<Employee> empls) throws SystemException {
		for (Employee empl : empls) {
	        if (Objects.nonNull(empl.getId())) {
	            throw new SystemException("Id must be null to add");
	        }
	        if (Objects.isNull(empl.getName())) {
	            throw new SystemException("Name must be NOT null to add");
	        }
	    }
		return employeeRepository.saveAll(empls);
	}

	@Override
	public Employee updateEmployee(Employee empl) throws SystemException {
		
		if(Objects.isNull(empl.getId()))
		{
			throw new SystemException("Id must be NOT NULL to Update");
		}
		return employeeRepository.save(empl);
	}

	@Override
	public List<Employee> updateListListEmployee(List<Employee> empls) throws SystemException {
		for (Employee empl : empls) {
	        if (Objects.isNull(empl.getId())) {
	            throw new SystemException("Id must be NOT NULL to Update");
	        }
	    }
		return employeeRepository.saveAll(empls);
	}

	@Override
	public void removeAllEmployees() {
		
		/*
		 * loads Entities before remove it 
		 * remove row by row 
		 * IS SLOW 
		 * can use with @PreRemove 
		 */
//		employeeRepository.deleteAll();
		
		/*
		 * Not Load Entities 
		 * SO Fast
		 * cann't use with @PreRemove
		 */
		
		employeeRepository.deleteAllInBatch();
		
	}

	@Override
	public void removeEmployeebyId(Long id) throws SystemException {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isEmpty())
		{
			throw new SystemException("Not have entit by this id: "+ id);
		}
		employeeRepository.deleteById(id);
		
	}

	@Override
	public void removeEmployeesbyIds(List<Long> ids) {
		  if (ids == null || ids.isEmpty()) {
		        throw new IllegalArgumentException("List of IDs must not be empty");
		    }
		employeeRepository.deleteAllById(ids);
		
	}

	@Override
	public List<Employee> findByNameStartsWith(String nameStr) {
		if (nameStr == null || nameStr.isEmpty()) {
	        throw new IllegalArgumentException("name must not be empty");
	    }
		return employeeRepository.findByNameStartsWith(nameStr);
	}
	
	

}
