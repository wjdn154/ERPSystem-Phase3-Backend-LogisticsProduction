package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.repository.notification;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.Notification;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {
    List<Notification> findByModuleAndPermission(ModuleType module, PermissionType permission);
}