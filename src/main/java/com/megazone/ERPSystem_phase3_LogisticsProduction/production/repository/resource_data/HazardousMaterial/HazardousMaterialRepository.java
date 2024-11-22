package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.HazardousMaterial;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.HazardousMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HazardousMaterialRepository extends JpaRepository<HazardousMaterial, Long>, HazardousMaterialRepositoryCustom {


    boolean existsByHazardousMaterialCode(String hazardousMaterialCode);

    Optional<HazardousMaterial> findByHazardousMaterialCode(String hazardousMaterialCode);


    boolean existsByHazardousMaterialCodeAndIdNot(String hazardousMaterialCode, Long id);


}
