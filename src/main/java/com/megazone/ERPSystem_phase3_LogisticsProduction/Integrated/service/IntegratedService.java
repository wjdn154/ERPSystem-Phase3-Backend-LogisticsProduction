package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class IntegratedService {

    private final RestClient integratedServiceClient;
    private final String baseUrl = "http://localhost:8082/api/integrated/";

    public void recentActivitySave(RecentActivityEntryDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("대시보드 api 호출 토큰 : " + token);

        try {
            String responseMessage =  integratedServiceClient.post()
                    .uri("recentActivity/save")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(String.class);
            System.out.println("최종 요청 URL: " + baseUrl + "recentActivity/save");
            if(responseMessage == null) {
                throw new RuntimeException("대시보드 서비스 null");
            }
        }
        catch(Exception e) {
            throw new RuntimeException("대시보드 서비스 호출 실패");
        }
    }

}
