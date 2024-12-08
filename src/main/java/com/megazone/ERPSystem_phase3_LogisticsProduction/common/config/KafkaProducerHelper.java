package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaProducerHelper {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topic, String key, Object data) {
        kafkaTemplate.send(topic, key, data)
                .thenAccept(result -> System.out.println("Kafka 메시지 전송 성공: " + result.getRecordMetadata()))
                .exceptionally(ex -> {
                    System.err.println("Kafka 메시지 전송 실패: " + ex.getMessage());
                    return null;
                });
    }

    public void sagaSendSuccessResponse(String requestId, String groupId) {
        kafkaTemplate.send("saga-response", Map.of(
                "requestId", requestId,
                "serviceGroup", groupId,
                "status", "SUCCESS"
        ));
    }

    public void sagaSendErrorResponse(String requestId, String groupId, String error) {
        kafkaTemplate.send("saga-response", Map.of(
                "requestId", requestId,
                "serviceGroup", groupId,
                "status", "FAILED",
                "error", error
        ));
    }
}