package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.planning.crp;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.Crp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrpRepository extends JpaRepository<Crp, Long>, CrpRepositoryCustom {
}
