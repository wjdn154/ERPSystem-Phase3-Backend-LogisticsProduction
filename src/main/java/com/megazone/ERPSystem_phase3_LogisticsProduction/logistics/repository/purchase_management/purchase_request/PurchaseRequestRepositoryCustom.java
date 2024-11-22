package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase_request;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseRequest;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;

public interface PurchaseRequestRepositoryCustom {

    List<PurchaseRequest> findBySearch(SearchDTO dto);
}
