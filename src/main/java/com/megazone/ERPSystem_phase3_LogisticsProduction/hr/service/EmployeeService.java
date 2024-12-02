package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.EmployeeShowToDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeAttendanceDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto.EmployeeOneDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeOneDTO getOneEmployee(Long employeeId);

    List<EmployeeOneDTO> getListEmployee(List<Long> employeeIdList);

    List<EmployeeAttendanceDTO> getEmployeeAttendances(Long employeeId);

    void updateEmployee(EmployeeShowToDTO employeeDto);

    void saveEmployee(EmployeeShowToDTO employeeShowToDTO);
}
