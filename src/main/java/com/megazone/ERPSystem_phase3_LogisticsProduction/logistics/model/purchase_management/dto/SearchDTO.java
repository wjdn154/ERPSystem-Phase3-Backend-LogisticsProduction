package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// 발주 요청 검색 조건 DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private Long clientId;
    private String state;


}
