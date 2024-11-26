package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatAmountWithSupplyAmountDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VatTypeService {

    private final RestClient financialServiceClient;
    private final String baseUrl = "http://localhost:8080/api/financial/";

    public VatTypeShowDTO getVatType(Long vatTypeId) {

        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("회계 api 호출 토큰 : " + token);
        Map<String, Long> requestBody = Map.of("vatTypeId", vatTypeId);

        try {
            VatTypeShowDTO vatDto =  financialServiceClient.post()
                    .uri("vatType/vatType/get")
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .retrieve()
                    .body(VatTypeShowDTO.class);
            System.out.println("최종 요청 URL: " + baseUrl + "vatType/vatType/get");
            if(vatDto == null) {
                throw new RuntimeException("부가세 유형 서비스 null");
            }
            return vatDto;
        }
        catch(Exception e) {
            throw new RuntimeException("부가세 유형 서비스 호출 실패");
        }
    }

    public List<VatTypeShowDTO> getVatTypeList(List<Long> vatTypeIdList) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        System.out.println("회계 api 호출 토큰 : " + token);

        try {
            List<VatTypeShowDTO> vatDtoList =  financialServiceClient.post()
                    .uri("vatType/vatType/getAll")
                    .header("Authorization", "Bearer " + token)
                    .body(vatTypeIdList)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<VatTypeShowDTO>>() {});
            System.out.println("최종 요청 URL: " + baseUrl + "vatType/vatType/getAll");
            if(vatDtoList == null) {
                throw new RuntimeException("부가세 유형 서비스 null");
            }
            return vatDtoList;
        }
        catch(Exception e) {
            throw new RuntimeException("부가세 유형 서비스 호출 실패");
        }
    }

    public BigDecimal vatAmountCalculate(VatAmountWithSupplyAmountDTO dto) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        System.out.println("회계 api 호출 토큰 : " + token);

        try {
            BigDecimal requestBigDecimal = financialServiceClient.post()
                    .uri("vatType/vatAmount/supplyAmount")
                    .header("Authorization", "Bearer " + token)
                    .body(dto)
                    .retrieve()
                    .body(BigDecimal.class);

            System.out.println("최종 요청 URL: " + baseUrl + "vatType/vatType/get");

            if(requestBigDecimal == null) {
                throw new RuntimeException("부가세 계산 서비스 null");
            }
            return requestBigDecimal;
        }
        catch(Exception e) {
            throw new RuntimeException("부가세 계산 서비스 호출 실패");
        }
    }
}
