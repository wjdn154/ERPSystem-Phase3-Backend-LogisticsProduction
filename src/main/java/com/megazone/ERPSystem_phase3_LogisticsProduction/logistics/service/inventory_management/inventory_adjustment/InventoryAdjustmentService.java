package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory_adjustment;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.AdjustmentRequestDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface InventoryAdjustmentService {
    void adjustInventory(AdjustmentRequestDTO adjustmentRequestDTO);

    @Transactional(readOnly = true)
    Long generateWorkNumber(LocalDate workDate);

    @Transactional(readOnly = true)
    Long generateSlipNumber(LocalDate slipDate);
}
