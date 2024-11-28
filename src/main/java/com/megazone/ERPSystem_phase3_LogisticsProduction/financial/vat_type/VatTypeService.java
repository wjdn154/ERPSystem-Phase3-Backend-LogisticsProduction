package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatAmountWithSupplyAmountDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
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
    private final ConcurrentHashMap<String, CompletableFuture<VatTypeShowDTO>> responseFutures = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CompletableFuture<List<VatTypeShowDTO>>> listResponseFutures = new ConcurrentHashMap<>();
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public VatTypeShowDTO getVatType(Long vatTypeId) {
        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<VatTypeShowDTO> future = new CompletableFuture<>();

        // 요청 데이터 생성 (JWT 토큰 포함)
        Map<String, Object> requestBody = Map.of(
                "vatTypeId", vatTypeId,
                "currentTenant", currentTenant,
                "requestId", requestId
        );

        // 응답 관리용 Future 저장
        responseFutures.put(requestId, future);

        // Kafka 메시지 전송
        kafkaTemplate.send("vat-type-request-topic", requestId, requestBody);

        try {
            // 비동기 응답 대기
            return future.join();
        } catch (Exception e) {
            responseFutures.remove(requestId);
            throw new RuntimeException("부가세 유형 서비스 호출 실패");
        }
    }

    @KafkaListener(topics = "vat-type-response-topic", groupId = "vat-type-service-group")
    public void handleVatTypeResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            VatTypeShowDTO vatTypeShowDTO = objectMapper.convertValue(response.get("data"), VatTypeShowDTO.class);

            // 요청 ID에 매칭되는 Future를 완료 상태로 변경
            CompletableFuture<VatTypeShowDTO> future = responseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeShowDTO);
            }
        } catch (Exception e) {
            System.err.println("응답 데이터 변환 실패: " + e.getMessage());
            CompletableFuture<VatTypeShowDTO> future = responseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
        }
    }

    public List<VatTypeShowDTO> getVatTypeList(List<Long> vatTypeIdList) {
        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<List<VatTypeShowDTO>> future = new CompletableFuture<>();

        // 요청 데이터 생성
        Map<String, Object> requestBody = Map.of(
                "vatTypeIdList", vatTypeIdList,
                "currentTenant", currentTenant,
                "requestId", requestId
        );

        // 응답 관리용 Future 저장
        listResponseFutures.put(requestId, future);

        // Kafka 메시지 전송
        kafkaTemplate.send("vat-type-list-request-topic", requestId, requestBody)
                .thenAccept(sendResult -> {
                    System.out.println("Kafka 메시지 전송 성공: " + sendResult.getRecordMetadata());
                })
                .exceptionally(ex -> {
                    System.err.println("Kafka 메시지 전송 실패: " + ex.getMessage());
                    listResponseFutures.remove(requestId);
                    future.completeExceptionally(ex); // 실패 처리
                    return null;
                });

        try {
            // 비동기 응답 대기
            return future.join();
        } catch (Exception e) {
            listResponseFutures.remove(requestId);
            System.err.println("에러 발생: " + e.getMessage());
            throw new RuntimeException("부가세 유형 리스트 서비스 호출 실패", e);
        }
    }

    @KafkaListener(topics = "vat-type-list-response-topic", groupId = "vat-type-service-group")
    public void handleVatTypeListResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            List<VatTypeShowDTO> vatTypeList = objectMapper.convertValue(
                    response.get("data"),
                    new TypeReference<List<VatTypeShowDTO>>() {}
            );

            // 요청 ID에 매칭되는 Future를 완료 상태로 변경
            CompletableFuture<List<VatTypeShowDTO>> future = listResponseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeList);
            }
        } catch (Exception e) {
            System.err.println("응답 데이터 변환 실패 : " + e.getMessage());
            CompletableFuture<List<VatTypeShowDTO>> future = listResponseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
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
