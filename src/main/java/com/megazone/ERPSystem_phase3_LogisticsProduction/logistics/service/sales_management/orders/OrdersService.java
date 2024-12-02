package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.sales_management.orders;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Orders;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.orders.OrdersCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.orders.OrdersResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.dto.orders.OrdersResponseDto;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<OrdersResponseDto> findAllOrders(SearchDTO dto);

    Optional<OrdersResponseDetailDto> findOrdersDetailById(Long id);

    OrdersResponseDetailDto createOrders(OrdersCreateDto createDto);

    // 주문서 등록 DTO -> Entity 변환 메소드
    Orders toEntity(OrdersCreateDto dto);

    Orders getOrders(OrdersCreateDto dto, Orders orders);

    OrdersResponseDetailDto updateOrders(Long id, OrdersCreateDto updateDto);

    String deleteOrders(Long id);
}
