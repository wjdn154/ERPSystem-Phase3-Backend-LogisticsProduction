package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.receiving_order;


import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.ReceivingOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.ReceivingOrderCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.ReceivingOrderResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.ReceivingOrderResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;

import java.util.List;
import java.util.Optional;

public interface ReceivingOrderService {
    List<ReceivingOrderResponseDto> findAllReceivingOrders(SearchDTO dto);

    Optional<ReceivingOrderResponseDetailDto> findReceivingOrderDetailById(Long id);

    ReceivingOrderResponseDetailDto createReceivingOrder(ReceivingOrderCreateDto createDto);

    // 입고지시서 등록 DTO -> Entity 변환 메소드
    ReceivingOrder toEntity(ReceivingOrderCreateDto dto);

    ReceivingOrder getReceivingOrder(ReceivingOrderCreateDto dto, ReceivingOrder newOrder);

    ReceivingOrderResponseDetailDto updatePurchaseOrder(Long id, ReceivingOrderCreateDto updateDto);

    String deleteReceivingOrder(Long id);
}
