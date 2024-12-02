package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.kafka.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.EmployeeService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.EmployeeServiceImpl;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeAttendanceDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeOneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class EmployeeListener {
    private final EmployeeService employeeService;
    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, CompletableFuture<EmployeeOneDTO>> getEmployeeOneResponseFutures; // 사원 단건 조회 event 이력
    private final ConcurrentHashMap<String, CompletableFuture<List<EmployeeOneDTO>>> getEmployeeListResponseFutures; // 사원 리스트 조회 event 이력
    private final ConcurrentHashMap<String, CompletableFuture<List<EmployeeAttendanceDTO>>> getEmployeeAttendanceListResponseFutures; // 사원 근태리스트 조회 event 이력

    @KafkaListener(topics = "employee-update", groupId = "employee-service-group")
    public void handleEmployeeUpdateResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("data")) {
                EmployeeShowToDTO employeeShowToDTO = objectMapper.convertValue(response.get("data"), EmployeeShowToDTO.class);
                employeeService.updateEmployee(employeeShowToDTO);
            } else if (response.containsKey("error")) {
//                String error = (String) response.get("error");
                // 에러로직 필요?
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }

    @KafkaListener(topics = "employee-save", groupId = "employee-service-group")
    public void handleEmployeeSaveResponse(Map<String, Object> response) {
        //        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("data")) {
                EmployeeShowToDTO employeeShowToDTO = objectMapper.convertValue(response.get("data"), EmployeeShowToDTO.class);
                employeeService.saveEmployee(employeeShowToDTO);
            } else if (response.containsKey("error")) {
//                String error = (String) response.get("error");
                // 에러로직
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }

    @KafkaListener(topics = "employee-showOne-response", groupId = "employee-service-group")
    public void handleGetEmployeeOneResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            EmployeeOneDTO vatTypeList = objectMapper.convertValue(response.get("data"), EmployeeOneDTO.class);

            CompletableFuture<EmployeeOneDTO> future = getEmployeeOneResponseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeList);
            }
        } catch (Exception e) {
            CompletableFuture<EmployeeOneDTO> future = getEmployeeOneResponseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
        }
    }


    @KafkaListener(topics = "employee-showList-response", groupId = "employee-service-group")
    public void handleGetEmployeeListResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            List<EmployeeOneDTO> vatTypeList = objectMapper.convertValue(response.get("data"),
                    new TypeReference<List<EmployeeOneDTO>>() {});

            CompletableFuture<List<EmployeeOneDTO>> future = getEmployeeListResponseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeList);
            }
        } catch (Exception e) {
            CompletableFuture<List<EmployeeOneDTO>> future = getEmployeeListResponseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
        }
    }


    @KafkaListener(topics = "employee-attendanceList-response", groupId = "employee-service-group")
    public void handleGetEmployeeAttendanceListResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            List<EmployeeAttendanceDTO> vatTypeList = objectMapper.convertValue(response.get("data"),
                    new TypeReference<List<EmployeeAttendanceDTO>>() {});

            CompletableFuture<List<EmployeeAttendanceDTO>> future = getEmployeeAttendanceListResponseFutures.remove(requestId);
            if (future != null) {
                future.complete(vatTypeList);
            }
        } catch (Exception e) {
            CompletableFuture<List<EmployeeAttendanceDTO>> future = getEmployeeAttendanceListResponseFutures.remove(requestId);
            if (future != null) {
                future.completeExceptionally(e);
            }
        }
    }
}
