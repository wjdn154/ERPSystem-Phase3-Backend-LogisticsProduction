package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto.ProductMaterialDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 두번째 페이지 해당 자재의 품목 리스트, 삭제 dto
 * */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListProductMaterialDTO {

    private Long id;
    private String materialCode;           //자재 코드
    private String materialName;           //자재 이름
    private List<ProductMaterialDTO> product;             //품목 리스트(한 자재에 여러개의 품목?)


}
