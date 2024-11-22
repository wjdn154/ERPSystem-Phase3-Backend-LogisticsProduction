package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * 회사 기본 정보 테이블
 * 회사 기본 정보 등록시 사용하는 테이블
 */
@Entity(name = "company")
@Table(name = "company")
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유식별자

    @Column(nullable = false) // 회사명
    private String name;

    @Column(nullable = false) // 관리자 이메일
    private String adminUsername;

}