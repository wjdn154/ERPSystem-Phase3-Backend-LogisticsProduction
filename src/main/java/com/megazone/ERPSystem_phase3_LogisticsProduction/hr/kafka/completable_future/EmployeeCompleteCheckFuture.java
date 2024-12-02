package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.kafka.completable_future;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeAttendanceDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeOneDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class EmployeeCompleteCheckFuture {
    @Bean
    public ConcurrentHashMap<String, CompletableFuture<EmployeeOneDTO>> getEmployeeOneResponseFutures() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public ConcurrentHashMap<String, CompletableFuture<List<EmployeeOneDTO>>> getEmployeeListResponseFutures() {
        return new ConcurrentHashMap<>();
    }

    @Bean
    public ConcurrentHashMap<String, CompletableFuture<List<EmployeeAttendanceDTO>>> getEmployeeAttendanceListResponseFutures() {
        return new ConcurrentHashMap<>();
    }
}
