package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.planning.mrp;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.mrp.MaterialInputStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialInputStatusRepository extends JpaRepository<MaterialInputStatus, Long>, MaterialInputStatusRepositoryCustom {
}
