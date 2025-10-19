package com.example.demo.useDto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.EmployeeRequestDTO;
import com.example.demo.DTO.EmployeeResponseDTO;
import com.example.demo.useDto.service.EmpService;

import jakarta.transaction.SystemException;

@RestController
@RequestMapping("empl")
public class emplController {

	@Autowired
	private EmpService empService;
	
	
	@GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(empService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return ResponseEntity.ok(empService.getAllEmployees());
    }
	
	@PostMapping("/add")
    public ResponseEntity<EmployeeResponseDTO> addEmployee(@RequestBody EmployeeRequestDTO dto) {
        return ResponseEntity.ok(empService.addEmployee(dto));
        
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(
	        @PathVariable Long id,
	        @RequestBody EmployeeRequestDTO dto) throws SystemException 
	{
		
	    return ResponseEntity.ok(empService.updateEmployee(id, dto));
	}
	
	

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        try {
        	empService.deleteEmployeeById(id);
            return ResponseEntity.ok("✅ Employee and related details/emails deleted successfully");
        } catch (SystemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
