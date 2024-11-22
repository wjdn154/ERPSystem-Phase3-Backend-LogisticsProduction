package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.sale;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Sale;

import java.util.List;

public interface SaleRepositoryCustom {
    List<Sale> findBySearch(SearchDTO dto);
}
