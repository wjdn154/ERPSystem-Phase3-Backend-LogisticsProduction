package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentStatus;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeOneDTO {
    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String registrationNumber;
    private String phoneNumber;
    private EmploymentStatus employmentStatus;
    private EmploymentType employmentType;
    private String email;
    private String address;
    private LocalDate hireDate;
    private boolean isHouseholdHead;
    private String profilePicture;
    private Long departmentId;
    private String departmentCode;
    private String departmentName; // 부서 이름
    private Long positionId;
    private String positionCode;
    private String positionName; // 직위 이름
    private Long titleId;
    private String titleCode;
    private String titleName; // 직책 이름
    private BankAccountDTO bankAccountDTO;
//    private String bankName;
//    private String bankAccountNumber; // 계좌 번호
}
