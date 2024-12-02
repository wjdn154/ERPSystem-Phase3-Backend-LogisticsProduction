package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.warehouse_management;


import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto.test.WarehouseResponseListDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto.test.WarehouseRequestTestDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto.test.WarehouseResponseTestDTO;

import java.util.List;

public interface WarehouseService {

    List<WarehouseResponseListDTO> getWarehouseList();

    WarehouseResponseTestDTO getWarehouseDetail(Long warehouseId);

    WarehouseResponseTestDTO createWarehouse(WarehouseRequestTestDTO requestDTO);

    WarehouseResponseTestDTO updateWarehouse(Long warehouseId, WarehouseRequestTestDTO requestDTO);

    String deleteWarehouse(Long id);

    // 연관된 테이블의 문제를 확인하고 메시지를 생성하는 메서드
    String getRelatedEntitiesIssues(Warehouse warehouse);
}
