package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.shipping_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.ShippingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, Long>, ShippingOrderRepositoryCustom {
}
