package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.KafkaProducerHelper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatAmountWithSupplyAmountDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class VatTypeService {

    private final RestClient financialServiceClient;
    private final ConcurrentHashMap<String, CompletableFuture<VatTypeShowDTO>> responseFutures;
    private final ConcurrentHashMap<String, CompletableFuture<List<VatTypeShowDTO>>> listResponseFutures;
    private final KafkaProducerHelper kafkaProducerHelper;


    public VatTypeShowDTO getVatType(Long vatTypeId) {
        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<VatTypeShowDTO> future = new CompletableFuture<>();
        responseFutures.put(requestId, future);

        kafkaProducerHelper.sendMessage("vat-type-request-topic", requestId, Map.of(
                "vatTypeId", vatTypeId,
                "currentTenant", currentTenant,
                "requestId", requestId
        ));

        try {
            return future.join();
        } catch (Exception e) {
            responseFutures.remove(requestId);
            throw new RuntimeException("부가세 유형 서비스 호출 실패");
        }
    }

    public List<VatTypeShowDTO> getVatTypeList(List<Long> vatTypeIdList) {
        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<List<VatTypeShowDTO>> future = new CompletableFuture<>();
        listResponseFutures.put(requestId, future);

        kafkaProducerHelper.sendMessage("vat-type-list-request-topic", requestId, Map.of(
                "vatTypeIdList", vatTypeIdList,
                "currentTenant", currentTenant,
                "requestId", requestId
        ));

        try {
            return future.join();
        } catch (Exception e) {
            listResponseFutures.remove(requestId);
            throw new RuntimeException("부가세 유형 리스트 서비스 호출 실패", e);
        }
    }

    public BigDecimal vatAmountCalculate(VatAmountWithSupplyAmountDTO dto) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        try {
            BigDecimal requestBigDecimal = financialServiceClient.post()
                    .uri("vatType/vatAmount/supplyAmount")
                    .header("Authorization", "Bearer " + token)
                    .body(dto)
                    .retrieve()
                    .body(BigDecimal.class);

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
