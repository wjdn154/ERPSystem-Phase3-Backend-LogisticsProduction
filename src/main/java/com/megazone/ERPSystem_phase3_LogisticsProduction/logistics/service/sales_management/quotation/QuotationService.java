package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.sales_management.quotation;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Quotation;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.quotation.QuotationCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.quotation.QuotationResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.quotation.QuotationResponseDto;

import java.util.List;
import java.util.Optional;

public interface QuotationService {
    List<QuotationResponseDto> findAllQuotations(SearchDTO dto);

    Optional<QuotationResponseDetailDto> findQuotationDetailById(Long id);

    QuotationResponseDetailDto createQuotation(QuotationCreateDto createDto);

    Quotation getQuotation(QuotationCreateDto dto, Quotation quotation);

    QuotationResponseDetailDto updateQuotation(Long id, QuotationCreateDto updateDto);

    String deleteQuotation(Long id);
}
