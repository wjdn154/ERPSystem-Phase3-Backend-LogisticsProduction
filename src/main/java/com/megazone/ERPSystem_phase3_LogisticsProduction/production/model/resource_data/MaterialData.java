package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.mrp.MaterialInputStatus;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.client.Client;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.enums.MaterialType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
* 자재 정보 관리
* */

@Entity(name = "production_material_data")
@Table(name = "production_material_data")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client supplier; // 공급자 / 외주 작업을 수행하는 공급업체(supplier) from 회계

    @OneToMany(mappedBy = "materialData", fetch = FetchType.LAZY) private List<MaterialHazardous> materialHazardous = new ArrayList<>(); //자재와 유해물질 다대다 중간 엔티티
    @OneToMany(mappedBy = "materialData", fetch = FetchType.LAZY) private List<MaterialProduct> materialProducts = new ArrayList<>(); //자재와 품목 다대다 중간 엔티티
    @OneToMany(mappedBy = "materialData", cascade = CascadeType.ALL, fetch = FetchType.LAZY) private List<MaterialInputStatus> materialInputStatusList;
//    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, fetch = FetchType.LAZY) private List<StandardBomMaterial> bomMaterials = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MaterialType materialType; //자재 테이블 참조. 자재유형  enum

    @Column(nullable = false, unique = true) private String materialCode; //자재 코드
    @Column(nullable = false) private String materialName; //자재 이름
    @Column(nullable = false) private Long stockQuantity; //재고 수량
    @Column(nullable = false) private BigDecimal purchasePrice; //구매 가격
    @Column(nullable = false) private Long quantity; // 수량 (단위 : kg)
    @Column(nullable = false) private BigDecimal averageWaste; // 산업 평균 폐기물 발생량 % (이 자재를 quantity * kg 생산할 때 발생하는 산업 평균 폐기물 발생량)
    @Column(nullable = false) private BigDecimal averagePowerConsumption;  // 산업 평균 에너지 소비량 MJ (이 자재를 quantity * kg 생산할 때 발생하는 산업 평균 에너지 소비량)

}
