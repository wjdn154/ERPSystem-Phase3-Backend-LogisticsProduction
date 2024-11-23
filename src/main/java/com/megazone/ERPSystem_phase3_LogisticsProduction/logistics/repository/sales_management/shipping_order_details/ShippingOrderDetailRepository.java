package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.shipping_order_details;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.ShippingOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOrderDetailRepository extends JpaRepository<ShippingOrderDetail, Long>, ShippingOrderDetailRepositoryCustom {
}