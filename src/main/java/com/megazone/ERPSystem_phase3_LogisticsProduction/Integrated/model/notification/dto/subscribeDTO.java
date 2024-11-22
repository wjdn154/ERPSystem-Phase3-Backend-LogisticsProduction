package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class subscribeDTO {
    private Long userId;
    private ModuleType module;
    private PermissionType permission;
}
