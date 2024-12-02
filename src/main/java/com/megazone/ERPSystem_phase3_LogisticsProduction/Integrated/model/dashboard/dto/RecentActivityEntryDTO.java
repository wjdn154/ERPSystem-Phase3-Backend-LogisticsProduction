package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentActivityEntryDTO {
    private String activityDescription;
    private ActivityType activityType;

    public static RecentActivityEntryDTO create(String activityDescription, ActivityType activityType) {
        return new RecentActivityEntryDTO(activityDescription, activityType);
    }
}