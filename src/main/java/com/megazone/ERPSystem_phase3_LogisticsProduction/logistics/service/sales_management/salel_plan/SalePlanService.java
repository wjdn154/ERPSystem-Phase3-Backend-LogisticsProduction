package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.sales_management.salel_plan;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.SalePlan;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.sale_plan.SalePlanCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.sale_plan.SalePlanResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.sale_plan.SalePlanResponseDto;

import java.util.List;
import java.util.Optional;

public interface SalePlanService {
    List<SalePlanResponseDto> findAllSalePlans(SearchDTO dto);

    Optional<SalePlanResponseDetailDto> findSalePlanDetailById(Long id);

    SalePlanResponseDetailDto createSalePlan(SalePlanCreateDto createDto);

    // 판매 계획 등록 DTO -> Entity 변환 메소드
    SalePlan toEntity(SalePlanCreateDto dto);

    SalePlan getSalePlan(SalePlanCreateDto dto, SalePlan salePlan);

    SalePlanResponseDetailDto updateSalePlan(Long id, SalePlanCreateDto updateDto);

    String deleteSalePlan(Long id);
}
