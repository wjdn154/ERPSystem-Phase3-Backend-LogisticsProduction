package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.purchase_request;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseRequest;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseRequestCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseRequestResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseRequestResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PurchaseRequestService {

    List<PurchaseRequestResponseDto> findAllPurchaseRequests(SearchDTO dto);

    Optional<PurchaseRequestResponseDetailDto> findPurchaseRequestDetailById(Long id);

    PurchaseRequestResponseDetailDto createPurchaseRequest(PurchaseRequestCreateDto creationDto);

    // 발주 요청 등록 DTO -> Entity 변환 메소드
    PurchaseRequest toEntity(PurchaseRequestCreateDto dto);

    @Transactional(readOnly = true)
    PurchaseRequest getPurchaseRequest(PurchaseRequestCreateDto dto, PurchaseRequest newRequest);

    PurchaseRequestResponseDetailDto updatePurchaseRequest(Long id, PurchaseRequestCreateDto updateDto);

    String deletePurchaseRequest(Long id);
}
