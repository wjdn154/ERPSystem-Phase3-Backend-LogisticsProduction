package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListHazardousMaterialDTO {

    private Long id;
    private String materialCode;           //자재 코드
    private String materialName;           //자재 이름
    private List<HazardousMaterialDTO> hazardousMaterial;   //유해물질 리스트


}
