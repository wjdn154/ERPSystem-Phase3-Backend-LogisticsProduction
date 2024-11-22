package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling;


import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.ProcessDetails;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.workcenter.Workcenter;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.Mps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 작업지시 엔티티
 */

/**
 * 작업지시 엔티티
 */

@Entity(name = "common_scheduling_production_order")
@Table(name = "common_scheduling_production_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; // 고유 ID

    @Column(nullable = false)
    private String name; // 작업지시명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mps_id", nullable = false)
    private Mps mps; // 연관된 MPS

    @Column(nullable = true)
    private String remarks; // 추가 설명 또는 비고

    @Column(nullable = true)
    private Boolean confirmed; // 확정 여부

    @Column(nullable = false)
    @Builder.Default
    private Boolean closed = false; // 마감 여부 (기본값: 미마감)

    @Column(nullable = false)
    private LocalDateTime startDateTime; // 작업 시작 날짜 및 시간

    @Column(nullable = false)
    private LocalDateTime endDateTime; // 작업 종료 날짜 및 시간

    @Column(nullable = false)
    private BigDecimal productionQuantity; // 생산 지시 수량

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id", nullable = false)
    private ProcessDetails processDetails; // 생산 공정과의 연관 관계

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workcenter_id", nullable = false)
    private Workcenter workcenter; // 작업 센터

    private LocalDateTime actualStartDateTime; // 실제 작업 시작 시간

    private LocalDateTime actualEndDateTime; // 실제 작업 종료 시간

    private BigDecimal actualProductionQuantity; // 실제 생산량

    @Column(nullable = false)
    private Long workers; // 작업 인원

    private Long actualWorkers; // 실제 작업 인원
}