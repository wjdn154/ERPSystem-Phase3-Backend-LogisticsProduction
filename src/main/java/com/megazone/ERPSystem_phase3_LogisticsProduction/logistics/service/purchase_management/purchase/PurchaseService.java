package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.purchase;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.Purchase;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<PurchaseResponseDto> findAllPurchases(SearchDTO dto);

    Optional<PurchaseResponseDetailDto> findPurchaseDetailById(Long id);

    PurchaseResponseDetailDto createPurchase(PurchaseCreateDto createDto);

    // 구매서 등록 DTO -> Entity 변환 메소드
    @Transactional(readOnly = true)
    Purchase toEntity(PurchaseCreateDto dto);

    Purchase getPurchase(PurchaseCreateDto dto, Purchase purchase);

    PurchaseResponseDetailDto updatePurchase(Long id, PurchaseCreateDto updateDto);

    String deletePurchase(Long id);
}
