package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.InventoryRequestDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.dto.InventoryResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public interface InventoryService {
    List<InventoryResponseDTO> getAllInventories();

    List<InventoryResponseDTO> getInventoriesByLocationId(Long locationId);

    InventoryResponseDTO createInventory(InventoryRequestDTO requestDTO);

    List<InventoryResponseDTO> getInventoriesByWarehouseId(Long warehouseId);

    Long generateNextInventoryNumber();

    List<InventoryResponseDTO> getInventoryByLocation(Long locationId);

    BigDecimal allInventoryCount();
}
