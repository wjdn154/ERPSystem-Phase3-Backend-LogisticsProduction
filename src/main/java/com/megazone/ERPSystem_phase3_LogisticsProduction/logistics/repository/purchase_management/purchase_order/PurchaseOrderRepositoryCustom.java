package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;

public interface PurchaseOrderRepositoryCustom {
    List<PurchaseOrder> findBySearch(SearchDTO dto);
}
