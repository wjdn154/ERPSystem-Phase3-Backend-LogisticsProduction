package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.purchase_request;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseRequestCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseRequestResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseRequestResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseRequestService {

    List<PurchaseRequestResponseDto> findAllPurchaseRequests(SearchDTO dto);

    Optional<PurchaseRequestResponseDetailDto> findPurchaseRequestDetailById(Long id);

    PurchaseRequestResponseDetailDto createPurchaseRequest(PurchaseRequestCreateDto creationDto);

    PurchaseRequestResponseDetailDto updatePurchaseRequest(Long id, PurchaseRequestCreateDto updateDto);

    String deletePurchaseRequest(Long id);
}
