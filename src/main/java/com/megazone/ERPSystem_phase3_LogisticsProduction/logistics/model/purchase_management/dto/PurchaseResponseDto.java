package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PurchaseResponseDto {
    private Long id;
    private String clientName;
    private LocalDate date;
    private String productName;
    private String warehouseName;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private String vatName;
    private String status;
    private Boolean accountingReflection;


}
