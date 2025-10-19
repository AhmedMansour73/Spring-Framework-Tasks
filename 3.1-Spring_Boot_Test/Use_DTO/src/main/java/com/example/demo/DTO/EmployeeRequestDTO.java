package com.example.demo.DTO;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
	
	private String name;
    private String username;
    private String password;
    private Double salary;
    

    private EmployeeDetailsRequestDTO details;     // one-to-one
    private Set<EmailRequestDTO> emails;           // one-to-many
    private Set<Long> departmentIds;

}
