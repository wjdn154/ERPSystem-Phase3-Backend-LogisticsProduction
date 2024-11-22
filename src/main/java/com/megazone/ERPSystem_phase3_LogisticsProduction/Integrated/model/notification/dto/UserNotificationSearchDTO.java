package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationSearchDTO {
    private Long userId;
    private Notification notification;
    private String module;
    private String permission;
    private String type;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime readAt;
    private boolean readStatus;
}
