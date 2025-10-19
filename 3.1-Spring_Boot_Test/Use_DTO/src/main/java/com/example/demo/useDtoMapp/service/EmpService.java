package com.example.demo.useDtoMapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.DepartmentResponseDTO;
import com.example.demo.DTO.EmailRequestDTO;
import com.example.demo.DTO.EmailResponseDTO;
import com.example.demo.DTO.EmployeeDetailsResponseDTO;
import com.example.demo.DTO.EmployeeRequestDTO;
import com.example.demo.DTO.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Email;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeDetails;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmailRepo;
import com.example.demo.repository.EmployeeDetailsRepo;
import com.example.demo.repository.EmployeeRepo;

import jakarta.transaction.SystemException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpService {
	
	private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final EmployeeDetailsRepo employeeDetailsRepo;
    private final EmailRepo emailRepo;
    
    private final ModelMapper modelMapper;
    
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO empldto)
    {
    	
    	Employee employee = new Employee();
    	employee.setName(empldto.getName());
    	employee.setUsername(empldto.getUsername());
        employee.setPassword(empldto.getPassword());
        employee.setSalary(empldto.getSalary());
        
        // ✅ 1. Details (OneToOne)
        if (empldto.getDetails() != null) {
            EmployeeDetails details = new EmployeeDetails();
            details.setBenefitPercentage(empldto.getDetails().getBenefitPercentage());
            details.setEmployee(employee); 
            employee.setDetails(details);
        }
        
        

        // ✅ 2. Emails (OneToMany)
        if (empldto.getEmails() != null) {
            Set<Email> emails = empldto.getEmails().stream().map(e -> {
                Email email = new Email();
                email.setEmail(e.getEmail());
                email.setPassword(e.getPassword());
                email.setEmployee(employee);
                return email;
            }).collect(Collectors.toSet());
            employee.setEmails(emails);
        }
        
      // ✅ 3. Departments (ManyToMany)
        if (empldto.getDepartmentIds() != null && !empldto.getDepartmentIds().isEmpty()) {
        	
            Set<Department> departments = new HashSet<>(departmentRepo.findAllById(empldto.getDepartmentIds()));
            employee.setDepartments(departments);
        }
        
     // ✅ 4. Save
        Employee saved = employeeRepo.save(employee);
    	
        return convertToDTO(saved);
    }
    
    
 // ✅ تحويل Entity إلى DTO
    private EmployeeResponseDTO convertToDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());

        // من details
        if (employee.getDetails() != null) {
            dto.setBenefitPercentage(employee.getDetails().getBenefitPercentage());
            dto.setTotalSalary(employee.getSalary() + (employee.getDetails().getBenefitPercentage() * employee.getSalary()));
            
            EmployeeDetailsResponseDTO detailsDTO = new EmployeeDetailsResponseDTO();
            detailsDTO.setId(employee.getDetails().getId());
            detailsDTO.setBenefitPercentage(employee.getDetails().getBenefitPercentage());
            dto.setDetails(detailsDTO);
        }

        // ✅ الأقسام
        if (employee.getDepartments() != null) {
        	dto.setDepartments(
        			employee.getDepartments().stream()
                    .map(dep -> new DepartmentResponseDTO(dep.getId(), dep.getName()))
                    .collect(Collectors.toList())
            );
        }
        
        // ✅ الإيميلات
        if (employee.getEmails() != null) {
        	dto.setEmails(
        			employee.getEmails().stream()
                    .map(e -> new EmailResponseDTO(e.getId(), e.getEmail() ))
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // ✅ جلب موظف واحد
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return convertToDTO(employee);
    }

    // ✅ جلب كل الموظفين
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // ----------------------------------//
    
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO dto) throws SystemException {
        // 1️⃣ تأكد أن الموظف موجود
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new SystemException("Employee not found with ID: " + id));

        // 2️⃣ حدث الحقول الأساسية
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            existing.setName(dto.getName().trim());
        }

        if (dto.getUsername() != null && !dto.getUsername().trim().isEmpty()) {
            existing.setUsername(dto.getUsername().trim());
        }

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            existing.setPassword(dto.getPassword());
        }
        if (dto.getSalary() != null) existing.setSalary(dto.getSalary());

        // 3️⃣ EmployeeDetails (OneToOne)
        if (dto.getDetails() != null ) {
            EmployeeDetails managedDetails = existing.getDetails();

            if (managedDetails != null) {
                managedDetails.setBenefitPercentage(dto.getDetails().getBenefitPercentage());
            } else {
                EmployeeDetails newDetails = new EmployeeDetails();
                newDetails.setBenefitPercentage(dto.getDetails().getBenefitPercentage());
                newDetails.setEmployee(existing);
                existing.setDetails(newDetails);
            }
        }

        // 4️⃣ Emails (OneToMany)
        if (dto.getEmails() != null) {
            // حافظ على الإيميلات القديمة الموجودة في قاعدة البيانات
            Set<Email> existingEmails = existing.getEmails();

            // ابحث عن الإيميلات الجديدة في الـ DTO
            List<String> newEmailsList = dto.getEmails()
                    .stream()
                    .map(EmailRequestDTO::getEmail)
                    .toList();

            // امسح الإيميلات اللي مش موجودة في الـ DTO
            existingEmails.removeIf(email -> !newEmailsList.contains(email.getEmail()));

            // حدّث أو أضف الإيميلات الجديدة
            for (EmailRequestDTO emailDTO : dto.getEmails()) {
                Email existingEmail = existingEmails.stream()
                        .filter(e -> e.getEmail().equals(emailDTO.getEmail()))
                        .findFirst()
                        .orElse(null);

                if (existingEmail != null) {
                    // تحديث فقط
                    existingEmail.setPassword(emailDTO.getPassword());
                } else {
                    // إنشاء جديد
                    Email newEmail = new Email();
                    newEmail.setEmail(emailDTO.getEmail());
                    newEmail.setPassword(emailDTO.getPassword());
                    newEmail.setEmployee(existing);
                    existingEmails.add(newEmail);
                }
            }
        }

        // 5️⃣ Departments (ManyToMany)
        if (dto.getDepartmentIds() != null && !dto.getDepartmentIds().isEmpty()) {
            Set<Department> departments = new HashSet<>(departmentRepo.findAllById(dto.getDepartmentIds()));
            existing.getDepartments().clear();
            existing.getDepartments().addAll(departments);
        }

        // 6️⃣ احفظ
        Employee saved = employeeRepo.save(existing);

        // 7️⃣ رجّع Response DTO
        return convertToDTO(saved);
    }

    
    
    public void deleteEmployeeById(Long id) throws SystemException {
        // 1️⃣ تأكد أن الموظف موجود
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new SystemException("Employee not found with ID: " + id));

        // 2️⃣ افصل العلاقة مع الأقسام (لكن لا تحذف الأقسام)
        if (existing.getDepartments() != null && !existing.getDepartments().isEmpty()) {
            existing.getDepartments().clear(); // فقط unlink
        }

        // 3️⃣ احذف Details لو موجود
        if (existing.getDetails() != null) {
        	employeeDetailsRepo.delete(existing.getDetails());
            existing.setDetails(null);
        }

        // 4️⃣ احذف الإيميلات لو موجودة
        if (existing.getEmails() != null && !existing.getEmails().isEmpty()) {
            emailRepo.deleteAll(existing.getEmails());
            existing.getEmails().clear();
        }

        // 5️⃣ احذف الموظف نفسه
        employeeRepo.delete(existing);
    }
    

}
