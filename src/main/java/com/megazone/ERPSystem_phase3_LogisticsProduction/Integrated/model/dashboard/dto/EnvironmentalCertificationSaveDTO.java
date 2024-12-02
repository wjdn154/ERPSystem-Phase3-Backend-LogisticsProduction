package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentalCertificationSaveDTO {
    private YearMonth yearMonth;
    private BigDecimal actualWasteGenerated;
    private BigDecimal actualEnergyConsumption;
    private BigDecimal averageWasteGenerated;
    private BigDecimal averageEnergyConsumption;
}
