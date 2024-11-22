package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.inventory_management.inventory;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.Inventory;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse_location.WarehouseLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long>, InventoryRepositoryCustom {
    boolean existsByWarehouseLocationId(Long warehouseLocationId);

    boolean existsByWarehouse(Warehouse warehouse);

    Optional<Object> findByInventoryNumberAndProductAndWarehouseLocation(Long pendingInventoryNumber, Product product, WarehouseLocation warehouseLocation);

    List<Inventory> findByWarehouseLocationId(Long locationId);
}
