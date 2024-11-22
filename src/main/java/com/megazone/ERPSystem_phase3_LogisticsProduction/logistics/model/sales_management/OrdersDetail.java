package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문서와의 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    @ToString.Exclude
    private Orders orders;

    // 품목과의 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 수량
    @Column(nullable = false)
    private Integer quantity;

    // 공급가액 (수량 * 단가)
    @Column(nullable = false)
    private BigDecimal supplyPrice;

    // 원화금액 (통화가 외자일때만 사용)
    @Column(nullable = true)
    private BigDecimal localAmount;

    // 부가세
    @Column
    private BigDecimal vat;

    // 비고
    @Column
    private String remarks;
}
