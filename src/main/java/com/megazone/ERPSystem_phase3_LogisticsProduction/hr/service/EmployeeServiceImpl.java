package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service;

import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.KafkaProducerHelper;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.multi_tenant.TenantContext;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.Employee;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeDataDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository.basic_information_management.Department.DepartmentRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository.basic_information_management.Employee.EmployeeRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeAttendanceDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeOneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ConcurrentHashMap<String, CompletableFuture<EmployeeOneDTO>> getEmployeeOneResponseFutures; // 사원 단건 조회 event 이력
    private final ConcurrentHashMap<String, CompletableFuture<List<EmployeeOneDTO>>> getEmployeeListResponseFutures; // 사원 리스트 조회 event 이력
    private final ConcurrentHashMap<String, CompletableFuture<List<EmployeeAttendanceDTO>>> getEmployeeAttendanceListResponseFutures; // 사원 근태리스트 조회 event 이력
    private final KafkaProducerHelper kafkaProducerHelper;


    @Override
    public EmployeeOneDTO getOneEmployee(Long employeeId) {

        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<EmployeeOneDTO> future = new CompletableFuture<>();
        getEmployeeOneResponseFutures.put(requestId, future);

        kafkaProducerHelper.sendMessage("employee-showOne-request",requestId,Map.of(
                "employeeId", employeeId,
                "currentTenant", currentTenant,
                "requestId", requestId));
        try {
            return future.join();
        }
        catch(Exception e) {
            getEmployeeOneResponseFutures.remove(future);
            throw new RuntimeException("사원 상세조회 서비스 호출 event 실패");
        }

    }

    @Override
    public List<EmployeeOneDTO> getListEmployee(List<Long> employeeIdList) {

        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<List<EmployeeOneDTO>> future = new CompletableFuture<>();
        getEmployeeListResponseFutures.put(requestId, future);

        kafkaProducerHelper.sendMessage("employee-showList-request",requestId,Map.of(
                "employeeIdList", employeeIdList,
                "currentTenant", currentTenant,
                "requestId", requestId));
        try {
            return future.join();
        }
        catch(Exception e) {
            getEmployeeListResponseFutures.remove(future);
            throw new RuntimeException("사원 리스트조회 서비스 호출 event 실패");
        }
    }


    @Override
    public List<EmployeeAttendanceDTO> getEmployeeAttendances(Long employeeId) {
        String currentTenant = TenantContext.getCurrentTenant();
        String requestId = UUID.randomUUID().toString();
        CompletableFuture<List<EmployeeAttendanceDTO>> future = new CompletableFuture<>();
        getEmployeeAttendanceListResponseFutures.put(requestId, future);

        kafkaProducerHelper.sendMessage("employee-attendanceList-request",requestId,Map.of(
                "employeeId", employeeId,
                "currentTenant", currentTenant,
                "requestId", requestId));
        try {
            return future.join();
        }
        catch(Exception e) {
            getEmployeeAttendanceListResponseFutures.remove(future);
            throw new RuntimeException("사원 근태이력 조회 서비스 호출 event 실패");
        }
    }

    @Override
    public void updateEmployee(EmployeeDataDTO employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).orElseThrow(
                () -> new RuntimeException("해당하는 사원정보가 없습니다."));

        employee.setDepartment(
                departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(
                        () -> new RuntimeException("해당하는 부서가 없습니다.")));

        employee.setEmployeeNumber(employeeDto.getRegistrationNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmploymentStatus(employeeDto.getEmploymentStatus());
        employee.setEmploymentType(employeeDto.getEmploymentType());

        employeeRepository.save(employee);

    }

    @Override
    public void saveEmployee(EmployeeShowToDTO employeeShowToDTO) {
        Employee employee = new Employee();

        employee.setDepartment(
                departmentRepository.findById(employeeShowToDTO.getDepartmentId()).orElseThrow(
                        () -> new RuntimeException("해당하는 부서가 없습니다.")));

        employee.setEmployeeNumber(employeeShowToDTO.getEmployeeNumber());
        employee.setEmail(employeeShowToDTO.getEmail());
        employee.setFirstName(employeeShowToDTO.getFirstName());
        employee.setLastName(employeeShowToDTO.getLastName());
        employee.setPhoneNumber(employeeShowToDTO.getPhoneNumber());
        employee.setEmploymentStatus(employeeShowToDTO.getEmploymentStatus());
        employee.setEmploymentType(employeeShowToDTO.getEmploymentType());
        employeeRepository.save(employee);
    }

}
