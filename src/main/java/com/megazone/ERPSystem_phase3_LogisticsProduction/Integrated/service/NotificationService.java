package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.dto.UserNotificationCreateAndSendDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RestClient notificationServiceClient;


    public void createAndSend(UserNotificationCreateAndSendDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try {
            String responseMessage =  notificationServiceClient.post()
                    .uri("createAndSend")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(String.class);
            if(responseMessage == null) {
                throw new RuntimeException("알림 서비스 null");
            }
        }
        catch(Exception e) {
            throw new RuntimeException("알림 서비스 호출 실패");
        }
    }
}
