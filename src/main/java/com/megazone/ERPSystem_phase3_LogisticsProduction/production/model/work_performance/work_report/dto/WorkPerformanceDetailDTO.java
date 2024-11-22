package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkPerformanceDetailDTO {

    private Long id;   //아이디
    private String name;    //이름
    private String description;   //설명

    private BigDecimal actualQuantity;    //실제 생산량
    private BigDecimal workCost;          //작업 비용

    private String workDailyReportCode;    //일별 보고서 코드
    private String workDailyReportName;    //일별 보고서 이름

    private Long productionOrderId;       //작업지시 아이디
    private String productionOrderName;   //작업지시 이름

    private String productCode;         //품목 코드
    private String productName;         //품목 이름


}
