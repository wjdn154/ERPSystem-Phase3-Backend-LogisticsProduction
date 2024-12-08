package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.kafka.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.KafkaProducerHelper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeDataDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.EmployeeService;
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
    private final KafkaProducerHelper kafkaProducerHelper;
    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<String, CompletableFuture<EmployeeOneDTO>> getEmployeeOneResponseFutures; // 사원 단건 조회 event 이력
    private final ConcurrentHashMap<String, CompletableFuture<List<EmployeeOneDTO>>> getEmployeeListResponseFutures; // 사원 리스트 조회 event 이력
    private final ConcurrentHashMap<String, CompletableFuture<List<EmployeeAttendanceDTO>>> getEmployeeAttendanceListResponseFutures; // 사원 근태리스트 조회 event 이력

    @KafkaListener(topics = "employee-update", groupId = "logistics-service-group")
    public void handleEmployeeUpdateResponse(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("updateData")) {
                EmployeeDataDTO employeeUpdateData = objectMapper.convertValue(response.get("updateData"), EmployeeDataDTO.class);
                employeeService.updateEmployee(employeeUpdateData);
                kafkaProducerHelper.sagaSendSuccessResponse(requestId,"logistics-service-group");
            } else if (response.containsKey("error")) {
                kafkaProducerHelper.sagaSendErrorResponse(requestId,"logistics-service-group",response.get("error").toString());
            }
        } catch (Exception e) {
            kafkaProducerHelper.sagaSendErrorResponse(requestId,"logistics-service-group",e.getMessage());
        }
        finally {
            TenantContext.setCurrentTenant("PUBLIC");
        }
    }


    @KafkaListener(topics = "employee-update-compensation", groupId = "logistics-service-group")
    public void handleEmployeeUpdateCompensation(Map<String, Object> response) {
        String requestId = (String) response.get("requestId");

        try {
            TenantContext.setCurrentTenant(response.get("tenant").toString());

            if (response.containsKey("originData")) {
                EmployeeDataDTO employeeDataDTO = objectMapper.convertValue(response.get("originData"), EmployeeDataDTO.class);
                System.out.println(employeeDataDTO.getLastName().concat(employeeDataDTO.getFirstName()) + " 사원 보상트랜잭션 실행");
                employeeService.updateEmployee(employeeDataDTO);
                System.out.println("보상 트랜잭션 완료");
//                kafkaProducerHelper.sagaSendSuccessResponse(requestId,"common-service-group");
            } else if (response.containsKey("error")) {
//                kafkaProducerHelper.sagaSendErrorResponse(requestId,"logistics-service-group",response.get("error").toString());
            }
        } catch (Exception e) {
//            kafkaProducerHelper.sagaSendErrorResponse(requestId,"logistics-service-group",e.getMessage());
            e.printStackTrace();
        } finally {
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
