package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.quotation;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation, Long>, QuotationRepositoryCustom {
}
