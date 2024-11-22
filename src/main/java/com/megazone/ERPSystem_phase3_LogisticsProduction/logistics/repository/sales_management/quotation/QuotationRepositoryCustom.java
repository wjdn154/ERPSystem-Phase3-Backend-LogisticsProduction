package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.quotation;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Quotation;

import java.util.List;

public interface QuotationRepositoryCustom {
    List<Quotation> findBySearch(SearchDTO dto);
}
