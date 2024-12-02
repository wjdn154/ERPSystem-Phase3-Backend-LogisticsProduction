package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.kafka_listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class VatTypeKafkaListener {
    private final ConcurrentHashMap<String, CompletableFuture<VatTypeShowDTO>> responseFutures;
    private final ConcurrentHashMap<String, CompletableFuture<List<VatTypeShowDTO>>> listResponseFutures;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "vat-type-response-topic", groupId = "vat-type-service-group")
    public void handleVatTypeResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            VatTypeShowDTO vatTypeShowDTO = objectMapper.convertValue(response.get("data"), VatTypeShowDTO.class);
            CompletableFuture<VatTypeShowDTO> future = responseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeShowDTO);
            }
        } catch (Exception e) {
            CompletableFuture<VatTypeShowDTO> future = responseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
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

            CompletableFuture<List<VatTypeShowDTO>> future = listResponseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeList);
            }
        } catch (Exception e) {
            CompletableFuture<List<VatTypeShowDTO>> future = listResponseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
        }
    }
}