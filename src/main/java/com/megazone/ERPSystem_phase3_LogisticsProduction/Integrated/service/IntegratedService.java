package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto.EnvironmentalCertificationSaveDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class IntegratedService {

    private final RestClient commonServiceClient;

    public void recentActivitySave(RecentActivityEntryDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try {
            String responseMessage =  commonServiceClient.post()
                    .uri("recentActivity/save")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(String.class);
            if(responseMessage == null) {
                throw new RuntimeException("대시보드 서비스 null");
            }
        }
        catch(Exception e) {
            throw new RuntimeException("대시보드 서비스 호출 실패");
        }
    }

    public void environmentalCertification(EnvironmentalCertificationSaveDTO requestData) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        try {
            String responseMessage =  commonServiceClient.post()
                    .uri("/environmental")
                    .header("Authorization", "Bearer " + token)
                    .body(requestData)
                    .retrieve()
                    .body(String.class);
            if(responseMessage == null) {
                throw new RuntimeException("대시보드 환경점수 null");
            }
        }
        catch(Exception e) {
            throw new RuntimeException("대시보드 환경점수 호출 실패");
        }
    }

}
