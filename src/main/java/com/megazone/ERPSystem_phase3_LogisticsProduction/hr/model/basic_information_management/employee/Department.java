package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 부서 정보 저장

@Data
@Entity(name="employee_department")
@Table(name = "employee_department")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="department_code", nullable = false,unique = true)
    private String departmentCode; // 부서번호

    @Column(name="department_name", nullable = false)
    private String departmentName; // 부서명

    @Column(name="location", nullable = false)
    private String location; // 부서 위치
}