package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.bom;

/*
    제품과 부품 간의 관계를 관리하며, 각 부품의 수량, 손실율, 유효기간 등을 포함한
    BOM(Bill of Materials) 정보를 저장하는 테이블.

    BOM 정전개(Forward Explosion)와 역전개(Backward Explosion) 모두 수행 (별도 테이블X)
    - BOM 정전개 (Forward Explosion)
      특정 품목을 기준으로 그 하위 품목들(부품들)을 조회하는 방법
    - BOM 역전개 (Backward Explosion)
      특정 부품이 어느 상위 품목들(제품들)에 포함되는지를 조회하는 방법
 */

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.outsourcing.OutsourcingType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "standard_bom")
@Table(name = "bom_standard_bom")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(exclude = {"product"})
public class StandardBom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // BOM 참조 Product

//    @OneToMany(mappedBy = "bom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<StandardBomMaterial> bomMaterials = new ArrayList<>(); // 중간 엔티티 리스트

    @Enumerated(EnumType.STRING) private OutsourcingType outsourcingType; // 외주 구분

    @Column(nullable = false) private String code; // BOM 지정코드
    @Column(nullable = false) private Double version; // BOM 버전
    @Column(nullable = false) private LocalDateTime createdDate; // BOM 생성일자
    @Column(nullable = false) private Double lossRate; // 손실율
    @Column(nullable = false) private LocalDate startDate; // Bom 유효시작일
    @Column(nullable = false) private LocalDate expiredDate; // Bom 유효종료일
    @Column(nullable = false) private Boolean isActive; // 사용 여부

    private String remarks; // 비고

}


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "child_product_id", nullable = true)
//    private Product childProduct;     // BOM이 참조하는 하위 Product

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_bom_id", nullable = true)
//    private StandardBom parentBom; // 재귀적 관계 설정 : 순환 참조가 발생하지 않도록 유효성 검사를 구현해야 함 (상위 BOM이 하위 BOM의 자식 중 하나라면 순환 참조가 발생한 것 or DFS 활용)

//    @OneToMany(mappedBy = "parentBom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<StandardBom> childBoms = new ArrayList<>();


//    @OneToMany(mappedBy = "standardBom", fetch = FetchType.LAZY)
//    @Builder.Default
//    private List<Mrp> mrps = new ArrayList<>(); // 연관 MRP