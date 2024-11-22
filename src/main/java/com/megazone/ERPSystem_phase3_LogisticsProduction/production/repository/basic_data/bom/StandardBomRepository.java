package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.basic_data.bom;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.bom.StandardBom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardBomRepository extends JpaRepository<StandardBom, Long> {

    // 특정 제품의 BOM 목록 조회
//    List<StandardBom> findByParentProductId(Long parentProductId);

    // 특정 제품을 자식으로 가지는 BOM 목록 조회 (역전개)
//    List<StandardBom> findByChildProductId(Long childProductId);

    // 활성화된 BOM 목록 조회
//    List<StandardBom> findByIsActiveTrue();
//
//    List<StandardBom> findByParentBomIsNull();
//    List<StandardBom> findByLevel(Long level);
    List<StandardBom> findByProductId(Long productId);

}
