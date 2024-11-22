package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>, PurchaseOrderRepositoryCustom {
}
