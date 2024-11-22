package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.warehouse_location_management;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse_location.WarehouseLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseLocationRepository extends JpaRepository<WarehouseLocation, Long>, WarehouseLocationRepositoryCustom {
    List<WarehouseLocation> findByWarehouseId(Long warehouseId);

    Optional<WarehouseLocation> findByWarehouseIdAndLocationName(Long warehouseId, String locationName);

    Optional<WarehouseLocation> findByIdAndWarehouseId(Long id, Long warehouseId);

    boolean existsByWarehouseIdAndLocationName(Long warehouseId, String locationName);

    boolean existsByWarehouse(Warehouse warehouse);
}
