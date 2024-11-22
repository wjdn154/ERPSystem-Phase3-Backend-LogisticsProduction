package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.work_performance.work_report;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.DailyAndMonthlyReportSearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.DailyReportDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.MonthlyReportDTO;

import java.util.List;

public interface WorkPerformanceRepositoryCustom {

    Long findProductionOrderIdByWorkPerformanceId(Long workPerformanceId);


    List<DailyReportDTO> dailyReport(DailyAndMonthlyReportSearchDTO dto);
    List<MonthlyReportDTO> monthlyReport(DailyAndMonthlyReportSearchDTO dto);

}
