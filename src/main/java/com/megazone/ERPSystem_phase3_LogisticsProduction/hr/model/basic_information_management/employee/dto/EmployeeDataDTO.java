package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentStatus;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDataDTO {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private EmploymentStatus employmentStatus;
    private EmploymentType employmentType;
    private String email;
    private String registrationNumber;
    private Long departmentId;
}