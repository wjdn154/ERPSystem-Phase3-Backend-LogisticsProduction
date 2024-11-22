package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.receiving_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.ReceivingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivingOrderRepository extends JpaRepository<ReceivingOrder, Long>, ReceivingOrderRepositoryCustom {
}
