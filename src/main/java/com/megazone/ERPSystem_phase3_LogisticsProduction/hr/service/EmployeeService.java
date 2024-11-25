package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatAmountWithSupplyAmountDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeAttendanceDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeOneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final RestClient humanResourceServiceClient;


    public EmployeeOneDTO getOneEmployee(Long employeeId) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("인사 api 호출 token : " + token);

        try {
            // RestClient 사용하여 GET 요청
            EmployeeOneDTO employeeDto = humanResourceServiceClient.get()
                    .uri(uriBuilder -> uriBuilder.path("employee/{id}").build(employeeId))
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .body(EmployeeOneDTO.class);
            if (employeeDto == null) {
                throw new RuntimeException("사원 조회 서비스가 없습니다.");
            }
            return employeeDto;
        } catch (Exception e) {
            throw new RuntimeException("사원 정보 조회 서비스 호출 실패", e);
        }
    }

    public List<EmployeeOneDTO> getListEmployee(List<Long> employeeIdList) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("인사 api 호출 token : " + token);

        try {
            // RestClient 사용하여 GET 요청
            List<EmployeeOneDTO> employeeDto = humanResourceServiceClient.post()
                    .uri("employee/workerAll")
                    .header("Authorization", "Bearer " + token)
                    .body(employeeIdList)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<EmployeeOneDTO>>() {});
            if (employeeDto == null) {
                throw new RuntimeException("사원 조회 서비스가 없습니다.");
            }
            return employeeDto;
        } catch (Exception e) {
            throw new RuntimeException("사원 정보 조회 서비스 호출 실패", e);
        }
    }


    public List<EmployeeAttendanceDTO> getEmployeeAttendances(Long employeeId) {
        String token = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        System.out.println("인사 api 호출 token : " + token);

        Map<String, Long> requestBody = new HashMap<>();
        requestBody.put("employeeId", employeeId);

        try {
            List<EmployeeAttendanceDTO> attendanceList = humanResourceServiceClient.post()
                    .uri("attendance/employee/recordsAll")
                    .header("Authorization", "Bearer " + token)
                    .body(requestBody)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<EmployeeAttendanceDTO>>() {});
            return attendanceList;
        } catch (Exception e) {
            throw new RuntimeException("사원 근태 정보 조회 서비스 호출 실패", e);
        }
    }
}
