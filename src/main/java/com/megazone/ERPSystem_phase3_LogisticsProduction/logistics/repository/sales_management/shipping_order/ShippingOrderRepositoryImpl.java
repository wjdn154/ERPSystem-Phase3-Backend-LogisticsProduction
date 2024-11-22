package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.shipping_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.QShippingOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.SaleState;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.ShippingOrder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShippingOrderRepositoryImpl implements ShippingOrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<ShippingOrder> findBySearch(SearchDTO dto) {
        QShippingOrder shippingOrder = QShippingOrder.shippingOrder;

        return queryFactory
                .selectFrom(shippingOrder)
                .where(
                        buildSearchCondition(dto)  // 검색 조건 빌드
                )
                .fetch();
    }

    // 검색 조건을 동적으로 생성
    private BooleanBuilder buildSearchCondition(SearchDTO dto) {
        QShippingOrder shippingOrder = QShippingOrder.shippingOrder;

        BooleanBuilder condition = new BooleanBuilder();  // BooleanBuilder로 동적 조건 생성

        // 발주 날짜 조건
        if (dto.getStartDate() != null && dto.getEndDate() != null) {
            condition.and(shippingOrder.date.between(dto.getStartDate(), dto.getEndDate()));
        }

        // 거래처 코드 조건
        if (dto.getClientId() != null) {
            condition.and(shippingOrder.shippingOrderDetails.any().product.client.id.eq(dto.getClientId()));  // 거래처 코드 조건 추가
        }

        // 상태 조건 (String을 Enum으로 변환)
        if (dto.getState() != null && !dto.getState().isEmpty()) {

            // DTO에서 상태를 가져와 Enum으로 변환
            SaleState enumState = SaleState.valueOf(dto.getState().toUpperCase());

            // QueryDSL Enum 값 비교
            condition.and(shippingOrder.state.eq(enumState));
        }

        return condition;
    }
}
