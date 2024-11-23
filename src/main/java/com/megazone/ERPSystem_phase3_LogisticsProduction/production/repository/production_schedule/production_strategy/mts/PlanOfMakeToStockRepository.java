package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.production_strategy.mts;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.production_strategy.PlanOfMakeToStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanOfMakeToStockRepository extends JpaRepository<PlanOfMakeToStock, Long>, PlanOfMakeToStockRepositoryCustom {
}