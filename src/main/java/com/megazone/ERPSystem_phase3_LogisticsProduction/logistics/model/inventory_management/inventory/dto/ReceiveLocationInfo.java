package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveLocationInfo {
    private Long receiveWarehouseLocationId;
    private Long receiveInventoryNumber;
    private Long quantity;
}
