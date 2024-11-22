package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.inventory_management.warehouse_transfer;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse_transfer.WarehouseTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseTransferRepository extends JpaRepository<WarehouseTransfer, Long>, WarehouseTransferRepositoryCustom {
    boolean existsBySendingWarehouse(Warehouse warehouse);

    boolean existsByReceivingWarehouse(Warehouse warehouse);
}
