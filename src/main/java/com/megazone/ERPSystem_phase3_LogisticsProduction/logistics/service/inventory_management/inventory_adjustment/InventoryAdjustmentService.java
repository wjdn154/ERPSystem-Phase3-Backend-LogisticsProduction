package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory_adjustment;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.AdjustmentRequestDTO;

public interface InventoryAdjustmentService {
    void adjustInventory(AdjustmentRequestDTO adjustmentRequestDTO);
}
