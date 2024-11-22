package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.equipment.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.enums.MaintenanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceHistoryDetailDTO {


    private String equipmentNum;                  //설비번호
    private String maintenanceManager;            //담당자. 타입 작업자?workerDTO?
    private MaintenanceType maintenanceType;      //유지보수 유형
    private BigDecimal maintenanceCost;           //유지보수에 드는 비용
    private Boolean maintenanceStatus;            //유지보수 진행 상태 (진행중/완료)
    private LocalDate maintenanceDate;            //유지보수 일자
    private LocalDate nextScheduleDate;           //다음 유지보수 예정일
    private String workcenterCode;                //설비엔티티의 작업장 코드.
    private String factoryCode;                   //설비엔티티의 공장 코드.
    private String title;                         //유지보수 제목
    private String maintenanceDetail;             //유지보수 상세내용

}
