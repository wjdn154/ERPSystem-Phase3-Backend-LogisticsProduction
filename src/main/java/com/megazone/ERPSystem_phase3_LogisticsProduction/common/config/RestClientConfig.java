package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient financialServiceClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080/api/financial/")
                .build();
    }

    @Bean
    public RestClient humanResourceServiceClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080/api/hr/")
                .build();
    }
}
