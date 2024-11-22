package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.shipping_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.ShippingOrder;

import java.util.List;

public interface ShippingOrderRepositoryCustom {
    List<ShippingOrder> findBySearch(SearchDTO dto);
}
