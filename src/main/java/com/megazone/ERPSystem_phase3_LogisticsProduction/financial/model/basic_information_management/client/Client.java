package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// 거래처 테이블
@Entity(name = "client")
@Table(name = "client")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code; // 거래처 코드

    private String printClientName; // 거래처명

    private String representativeName; // 대표자명
}