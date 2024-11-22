package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.WorkerAssignmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ListWorkerAttendanceDTO {

    private Long id;
    private String employeeNumber;     //employee 의 사원번호
    private String employeeFirstName;
    private String employeeLastName;
    private List<WorkerAttendanceDTO> workerAttendance;
    private List<WorkerAssignmentDTO> workerAssignment;

}
