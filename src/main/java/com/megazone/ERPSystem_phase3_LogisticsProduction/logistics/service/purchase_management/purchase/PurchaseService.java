package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.purchase;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<PurchaseResponseDto> findAllPurchases(SearchDTO dto);

    Optional<PurchaseResponseDetailDto> findPurchaseDetailById(Long id);

    PurchaseResponseDetailDto createPurchase(PurchaseCreateDto createDto);

    PurchaseResponseDetailDto updatePurchase(Long id, PurchaseCreateDto updateDto);

    String deletePurchase(Long id);
}
