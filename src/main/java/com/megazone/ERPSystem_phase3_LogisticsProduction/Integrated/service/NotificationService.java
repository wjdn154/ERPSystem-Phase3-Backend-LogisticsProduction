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
    private final String baseUrl = "http://localhost:8082/api/notifications/";


    public void createAndSend(UserNotificationCreateAndSendDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("알림 api 호출 토큰 : " + token);

        try {
            String responseMessage =  notificationServiceClient.post()
                    .uri("createAndSend")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(String.class);
            System.out.println("최종 요청 URL: " + baseUrl + "createAndSend");
            if(responseMessage == null) {
                throw new RuntimeException("알림 서비스 null");
            }
        }
        catch(Exception e) {
            throw new RuntimeException("알림 서비스 호출 실패");
        }
    }
}
