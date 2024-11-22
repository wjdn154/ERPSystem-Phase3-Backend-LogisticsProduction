package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.materialData;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.MaterialData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialDataRepository extends JpaRepository<MaterialData, Long> , MaterialDataRepositoryCustom{


    boolean existsByMaterialCode(String materialCode);

    boolean existsByMaterialCodeAndIdNot(String materialCode, Long id);

    List<MaterialData> findAllByOrderByIdDesc();

}
