package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.work_performance.quality_control.QualityInspection;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.quality_control.DefectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectTypeRepository extends JpaRepository<DefectType, Long> , DefectTypeRepositoryCustom{
}
