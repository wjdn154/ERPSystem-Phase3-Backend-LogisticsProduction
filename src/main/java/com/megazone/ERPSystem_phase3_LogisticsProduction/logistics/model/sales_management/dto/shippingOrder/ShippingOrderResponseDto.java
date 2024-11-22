package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.shippingOrder;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ShippingOrderResponseDto {
    private Long id;
    private String clientName;
    private String managerName;
    private String warehouseName;
    private String productName;
    private LocalDate date;
    private LocalDate shippingDate;
    private Integer totalQuantity;
    private String status;
    private String remarks;
}
