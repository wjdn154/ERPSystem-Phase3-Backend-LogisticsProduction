package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.inventory_management.inventory;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.InventoryResponseDTO;

import java.util.List;

public interface InventoryRepositoryCustom {
    List<InventoryResponseDTO> findAllInventories();

    List<InventoryResponseDTO> findInventoriesByLocationId(Long locationId);

    boolean existsByInventoryNumber(Long inventoryNumber);

    List<InventoryResponseDTO> findInventoriesByWarehouseId(Long warehouseId);

    Long findMaxInventoryNumber();
}
