package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.Purchase;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;

public interface PurchaseRepositoryCustom {
    List<Purchase> findBySearch(SearchDTO dto);
}
