package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration;

import jakarta.persistence.*;
import lombok.*;

/**
 * 품목그룹 엔티티
 * 품목그룹에 대한 정보가 있는 엔티티
 */

@Entity(name = "product_group")
@Table(name = "product_group")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroup {

    // 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 품목 그룹 코드
    @Column(nullable = false, unique = true)
    private String code;

    // 품목 그룹 명
    @Column(nullable = false)
    private String name;

    // 폼목 그룹 사용 여부
    @Column(nullable = false)
    @Builder.Default
    private boolean isActive = true;

    public void deactivate() {
        this.isActive = false;
    }

    public void reactivate() {
        this.isActive = true;
    }
}