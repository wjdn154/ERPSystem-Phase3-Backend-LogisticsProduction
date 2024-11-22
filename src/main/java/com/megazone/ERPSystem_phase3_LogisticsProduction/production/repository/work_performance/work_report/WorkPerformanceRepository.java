package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.work_performance.work_report;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.WorkPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkPerformanceRepository extends JpaRepository<WorkPerformance, Long> , WorkPerformanceRepositoryCustom {

    List<WorkPerformance> findAllByOrderByIdDesc();

    List<WorkPerformance> findByProductionOrderId(Long productionOrderId);

}
