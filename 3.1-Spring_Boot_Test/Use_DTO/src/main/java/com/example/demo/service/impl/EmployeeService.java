package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Email;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmailRepo;
import com.example.demo.repository.EmployeeRepo;

import jakarta.transaction.SystemException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
//	@Autowired
//	private EmployeeRepo employeeRepo;
	private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final EmailRepo emailRepo;
    
	public Employee getEmployeeById(Long id)
	{
		return employeeRepo.findById(id).get();
	}
	
	public List<Employee> getEmployees()
	{
		return employeeRepo.findAll();
	}
	
	public Employee addEmployee(Employee employee) throws SystemException
	{
		if(Objects.nonNull(employee.getId()))
		{
			throw new SystemException("Id must be null to add");
		}
		
		// ✅ 1.  OneToOne EmployeeDetails
	    if (employee.getDetails() != null) {
	        employee.getDetails().setEmployee(employee);
	    }
	    
	 // ✅ 2. اربط العلاقة OneToMany لو عندك Emails
	    if (employee.getEmails() != null) {
	        employee.getEmails().forEach(email -> email.setEmployee(employee));
	    }

	    // ✅ 3. اربط العلاقة ManyToMany (Departments)
	    // هنا مش محتاج setEmployee لأن العلاقة ManyToMany
	    // لكن لو الطرف التاني (Department) مش محفوظ، خليه محفوظ قبل كده
	    // cascade في ManyToMany بيحتاج الكيانات تكون موجودة فعلاً
		
	    return employeeRepo.save(employee);
	}
	
	public Employee addEmployee(Employee employee, Set<Long> departmentIds) throws SystemException {
        
       
        if (employee.getId() != null) {
            throw new SystemException("Id must be null when adding a new employee");
        }

        
        if (employee.getDetails() != null) {
            employee.getDetails().setEmployee(employee);
        }

        
        if (departmentIds != null && !departmentIds.isEmpty()) {
            Set<Department> departments = new HashSet<>(departmentRepo.findAllById(departmentIds));

            if (departments.isEmpty()) {
                throw new SystemException("No valid departments found for the given IDs");
            }

            employee.setDepartments(departments);
        }

       
        if (employee.getEmails() != null) {
            employee.getEmails().forEach(email -> email.setEmployee(employee));
        }

        
        return employeeRepo.save(employee);
    }
	

	@Transactional
	public Employee updateEmployee(Employee employee) throws SystemException
	{
		if(Objects.isNull(employee.getId()))
		{
			throw new SystemException("Id must be NOT NULL to add");
		}
		
		
		Employee existing = employeeRepo.findById(employee.getId())
	            .orElseThrow(() -> new SystemException("Employee not found"));

	    if (employee.getName() != null) existing.setName(employee.getName());
	    if (employee.getUsername() != null) existing.setUsername(employee.getUsername());
	    if (employee.getPassword() != null) existing.setPassword(employee.getPassword());
	    if (employee.getSalary() != null) existing.setSalary(employee.getSalary());

	    if (employee.getDetails() != null) {
	        EmployeeDetails managedDetails = existing.getDetails();

	        if (managedDetails != null) {
	            // ✅ حدّث القيم فقط
	            managedDetails.setBenefitPercentage(employee.getDetails().getBenefitPercentage());
	        } else {
	            // ✅ اربط علاقة جديدة بطريقة سليمة
	            EmployeeDetails newDetails = employee.getDetails();

	            // 👇 النقطة الأهم: اربط الاتجاهين
	            newDetails.setEmployee(existing);
	            existing.setDetails(newDetails);
	        }
	    }

	    if (employee.getEmails() != null) {
	    	existing.getEmails().clear();

	        for (Email newEmail : employee.getEmails()) {
	            if (newEmail.getId() != null) {
	               
	            	Email existingEmail = emailRepo.findById(newEmail.getId())
	            			.orElseThrow(() -> new SystemException("Email not found"));
	                existingEmail.setEmail(newEmail.getEmail());
	                existingEmail.setPassword(newEmail.getPassword());
	                existingEmail.setEmployee(existing);
	                existing.getEmails().add(existingEmail);
	            } else {
	                newEmail.setEmployee(existing);
	                existing.getEmails().add(newEmail);
	            }
	        }
	    }

	    if (employee.getDepartments() != null && !employee.getDepartments().isEmpty()) {
	        
	    	Set<Long> departmentIds = employee.getDepartments()
	                .stream()
	                .map(Department::getId)
	                .collect(Collectors.toSet());

	        Set<Department> managedDepartments = new HashSet<>(departmentRepo.findAllById(departmentIds));

	        existing.getDepartments().clear(); 
	        existing.getDepartments().addAll(managedDepartments); 
	    }
		
		return employeeRepo.save(existing);
	}
	
	@Transactional
	public void deleteEmployeeById(Long employeeId) throws SystemException {
	   
		Employee existing = employeeRepo.findById(employeeId)
	            .orElseThrow(() -> new SystemException("Employee not found"));

	    
		existing.getDepartments().clear();
	    employeeRepo.save(existing); 
	    
	    employeeRepo.delete(existing);
	}
}


/*
 * Helper Method
 */

/*
 * 
public void addEmail(Email email) {
    if (emails == null) emails = new HashSet<>();
    emails.add(email);
    email.setEmployee(this);
}

public void addDetails(EmployeeDetails details) {
    this.details = details;
    details.setEmployee(this);
}

employee.addDetails(employee.getDetails());
employee.getEmails().forEach(employee::addEmail);

 */
