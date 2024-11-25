package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatAmountWithSupplyAmountDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VatTypeService {

    private final RestClient financialServiceClient;

    public VatTypeShowDTO getVatType(Long vatTypeId) {

        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Map<String, Long> requestBody = Map.of("vatTypeId", vatTypeId);

        try {
            VatTypeShowDTO test =  financialServiceClient.post()
                    .uri("vatType/vatType/get")
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .retrieve()
                    .body(VatTypeShowDTO.class);
            if(test == null) {
                throw new RuntimeException("부가세 유형 서비스 null");
            }
            return test;
        }
        catch(Exception e) {
            throw new RuntimeException("부가세 유형 서비스 호출 실패");
        }
    }

    public BigDecimal vatAmountCalculate(VatAmountWithSupplyAmountDTO dto) {
        try {
            return financialServiceClient.post()
                    .uri("vatType/vatAmount/supplyAmount")
                    .body(dto)
                    .retrieve()
                    .body(BigDecimal.class);
        }
        catch(Exception e) {
            throw new RuntimeException("부가세 계산 서비스 호출 실패");
        }
    }
}
