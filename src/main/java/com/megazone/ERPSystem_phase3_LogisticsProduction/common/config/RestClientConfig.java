package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final SecretManagerConfig secretManagerConfig;

    @Bean
    public RestClient financialServiceClient() {
        return RestClient.builder()
                .baseUrl(secretManagerConfig.getFinancialServiceUrl())
                .build();
    }

    @Bean
    public RestClient humanResourceServiceClient() {
        return RestClient.builder()
                .baseUrl(secretManagerConfig.getHumanResourceServiceUrl())
                .build();
    }
}
