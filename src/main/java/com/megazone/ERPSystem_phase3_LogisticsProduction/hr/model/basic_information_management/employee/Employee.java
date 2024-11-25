package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentStatus;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;

import java.time.LocalDate;

// 사원 엔티티

@Data
@Entity(name = "employee")
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department", "users"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="employee_number",unique = true)
    private String employeeNumber; // 사원 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false) // 부서 참조
    private Department department;

    @OneToOne(mappedBy = "employee",fetch = FetchType.LAZY, cascade = CascadeType.ALL , orphanRemoval = true) // Users 랑 1대1 참조
    private Users users;

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    private String firstName; // 이름

    @Column(nullable = false)
    private String lastName; // 성

    @Column(nullable = false)
    private String phoneNumber; // 휴대폰 번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentStatus employmentStatus; // 고용 상태

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentType employmentType; // 고용 유형

}