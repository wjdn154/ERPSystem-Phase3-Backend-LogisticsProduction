package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerDTO {
    private String name;
    private String position;
    private String workcenterName;
}
