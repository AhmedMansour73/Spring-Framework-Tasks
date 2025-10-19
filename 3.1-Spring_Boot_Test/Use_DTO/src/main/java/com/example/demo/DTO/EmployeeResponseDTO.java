package com.example.demo.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
	
	private Long id;
    private String name;
    private Double salary;
    private Double benefitPercentage;
    private Double totalSalary; // محسبة

    private EmployeeDetailsResponseDTO details;
    private List<DepartmentResponseDTO> departments;
    private List<EmailResponseDTO> emails;
}
