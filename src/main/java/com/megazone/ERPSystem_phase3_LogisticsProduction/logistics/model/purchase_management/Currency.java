package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 통화 엔티티
 * 통화 등록 시 사용되는 엔티티
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Currency {

    // 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 통화 코드
    @Column(nullable = false, unique = true)
    private String code;

    // 통화명
    @Column(nullable = false)
    private String name;

    // 환율
    @Column(nullable = false)
    private BigDecimal exchangeRate;

    // 날짜
    @Column(nullable = false)
    private LocalDate date;

}
