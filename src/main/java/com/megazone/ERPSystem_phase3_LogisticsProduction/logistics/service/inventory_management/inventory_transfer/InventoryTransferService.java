package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory_transfer;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.TransferRequestDTO;

public interface InventoryTransferService {
    void transferInventory(TransferRequestDTO transferRequestDTO);
}
