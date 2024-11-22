package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository.basic_information_management.Department;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.dto.DepartmentShowDTO;

import java.util.List;

public interface DepartmentRepositoryCustom {
    List<DepartmentShowDTO> findAllDepartments();
}
