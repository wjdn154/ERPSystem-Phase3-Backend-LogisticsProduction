package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAndMonthlyReportSearchDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
