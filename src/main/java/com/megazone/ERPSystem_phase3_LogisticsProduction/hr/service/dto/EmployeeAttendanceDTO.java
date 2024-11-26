package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.Enum.AttendanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAttendanceDTO {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private String employeeNumber;
    private String attendanceCode;
    private Long positionId;
    private String positionName;
    private LocalDate date;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private AttendanceStatus status;
}
