package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Routing 정보 관리 테이블 : 생산 과정에서 공정 간의 흐름을 정의
 * 제조 과정에서 어떤 공정을 어떤 순서로 거칠지 명시
 * "재료 준비 -> 절단 -> 용접 -> 검사 -> 포장"과 같은 일련의 공정 순서
 */

@Entity(name = "process_routing")
@Table(name = "process_routing", indexes = {
        @Index(name = "idx_process_routing_code", columnList = "process_routing_code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"routingSteps"})
public class ProcessRouting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; // PK

    @Column(name = "process_routing_code", nullable = false, unique = true)
    private String code; // Routing 지정코드

    @Column(nullable = false)
    private String name; // Routing 이름

    @Column(nullable = false)
    private String description; // Routing 설명

    @Column(nullable = false)
    private boolean isStandard; // 표준 여부

    @Column(nullable = false)
    private boolean isActive; // 사용 여부

    @OneToMany(mappedBy = "processRouting", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("stepOrder ASC") // stepOrder 필드를 기준으로 오름차순 정렬
    @Builder.Default
    @JsonManagedReference
    private List<RoutingStep> routingSteps = new ArrayList<>(); // 연관 RoutingStep 목록

    @OneToMany(mappedBy = "processRouting")
    @Builder.Default
    private List<Product> products = new ArrayList<>();

}
