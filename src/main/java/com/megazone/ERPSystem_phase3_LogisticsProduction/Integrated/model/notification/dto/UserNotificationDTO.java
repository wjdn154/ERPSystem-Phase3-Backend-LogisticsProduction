package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.Notification;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.NotificationType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationDTO {
    private Long userId;
    private Notification notification;
    private ModuleType module;
    private PermissionType permission;
    private NotificationType type;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime readAt;
    private boolean readStatus;
}
