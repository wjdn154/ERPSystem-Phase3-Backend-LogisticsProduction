package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.planning.mrp;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.mrp.Mrp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MrpRepository extends JpaRepository<Mrp, Long>, MrpRepositoryCustom {
}
