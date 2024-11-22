package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSubscriptionDTO {
    private final ModuleType module;
    private final PermissionType permission;
}