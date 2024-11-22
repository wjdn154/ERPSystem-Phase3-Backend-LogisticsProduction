package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase_request;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseRequest;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long>, PurchaseRequestRepositoryCustom {
    boolean existsByReceivingWarehouse(Warehouse warehouse);


}
