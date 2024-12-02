package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.sales_management.shipping_order;



import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.ShippingOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.shippingOrder.ShippingOrderCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.shippingOrder.ShippingOrderResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.shippingOrder.ShippingOrderResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ShippingOrderService {
    List<ShippingOrderResponseDto> findAllShippingOrders(SearchDTO dto);

    Optional<ShippingOrderResponseDetailDto> findShippingOrderDetailById(Long id);

    ShippingOrderResponseDetailDto createShippingOrder(ShippingOrderCreateDto createDto);

    // 출하지시서 등록 DTO -> Entity 변환 메소드
    ShippingOrder toEntity(ShippingOrderCreateDto dto);

    @Transactional(readOnly = true)
    ShippingOrder getShippingOrder(ShippingOrderCreateDto dto, ShippingOrder newOrder);

    ShippingOrderResponseDetailDto updateShippingOrder(Long id, ShippingOrderCreateDto updateDto);

    String deleteShippingOrder(Long id);
}
