package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.repository.notification;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

    @Query("SELECT u.notification.id FROM user_notification u WHERE u.userId = :userId")
    List<Long> findNotificationIdsByUserId(Long userId);

    List<UserNotification> findByUserIdAndReadStatusFalse(Long userId);
    List<UserNotification> findByUserIdOrderByCreateAtDesc(Long userId);

    Optional<UserNotification> findByUserIdAndNotificationId(Long userId, Long notificationId);


}