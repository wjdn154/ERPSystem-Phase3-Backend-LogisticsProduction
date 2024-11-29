package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.completable_future;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CompleteCheckFuture {

    @Bean
    public ConcurrentHashMap<String, CompletableFuture<VatTypeShowDTO>> responseFutures() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public ConcurrentHashMap<String, CompletableFuture<List<VatTypeShowDTO>>> listResponseFutures() {
        return new ConcurrentHashMap<>();
    }
}
