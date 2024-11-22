package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.enums.WarehouseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponseDTO {
    private Long id;
    private String code;
    private String name;
    private WarehouseType warehouseType;
    private String productionProcess;
    private Boolean isActive;
}
