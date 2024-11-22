package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.basic_information_management.warehouse;


import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto.test.WarehouseResponseListDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto.test.WarehouseResponseTestDTO;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepositoryCustom {
    List<WarehouseResponseListDTO> findWarehouseList();

    Optional<WarehouseResponseTestDTO> findWarehouseDetailById(Long warehouseId);
}
