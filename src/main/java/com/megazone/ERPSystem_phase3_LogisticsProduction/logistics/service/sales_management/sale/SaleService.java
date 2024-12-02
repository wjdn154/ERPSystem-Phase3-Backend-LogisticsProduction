package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.sales_management.sale;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Sale;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.sale.SaleCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.sale.SaleResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.sale.SaleResponseDto;

import java.util.List;
import java.util.Optional;

public interface SaleService {
    List<SaleResponseDto> findAllSales(SearchDTO dto);

    Optional<SaleResponseDetailDto> findSaleDetailById(Long id);

    SaleResponseDetailDto createSale(SaleCreateDto createDto);

    // 판매서 등록 DTO -> Entity 변환 메소드
    Sale toEntity(SaleCreateDto dto);

    Sale getSale(SaleCreateDto dto, Sale sale);

    SaleResponseDetailDto updateSale(Long id, SaleCreateDto updateDto);

    String deleteSale(Long id);
}
