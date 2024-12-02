package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.completable_future;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeAttendanceDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class VatTypeCompleteCheckFuture {

    @Bean
    public ConcurrentHashMap<String, CompletableFuture<VatTypeShowDTO>> getTypeResponseFutures() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public ConcurrentHashMap<String, CompletableFuture<List<VatTypeShowDTO>>> vatTypeListResponseFutures() {
        return new ConcurrentHashMap<>();
    }

}
