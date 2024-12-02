package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory_transfer;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.TransferRequestDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface InventoryTransferService {
    void transferInventory(TransferRequestDTO transferRequestDTO);

    @Transactional(readOnly = true)
    Long generateWorkNumber(LocalDate transferDate);

    Long generateSlipNumber(LocalDate transferDate);
}
