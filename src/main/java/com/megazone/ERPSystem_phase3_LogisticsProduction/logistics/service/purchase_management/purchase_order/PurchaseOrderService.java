package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.purchase_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {
    List<PurchaseOrderResponseDto> findAllPurchaseOrders(SearchDTO dto);

    Optional<PurchaseOrderResponseDetailDto> findPurchaseOrderDetailById(Long id);

    PurchaseOrderResponseDetailDto createPurchaseOrder(PurchaseOrderCreateDto createDto);

    // 발주서 등록 DTO -> Entity 변환 메소드
    PurchaseOrder toEntity(PurchaseOrderCreateDto dto);

    PurchaseOrder getPurchaseOrder(PurchaseOrderCreateDto dto, PurchaseOrder newOrder);

    PurchaseOrderResponseDetailDto updatePurchaseOrder(Long id, PurchaseOrderCreateDto updateDto);

    String deletePurchaseOrder(Long id);
}
