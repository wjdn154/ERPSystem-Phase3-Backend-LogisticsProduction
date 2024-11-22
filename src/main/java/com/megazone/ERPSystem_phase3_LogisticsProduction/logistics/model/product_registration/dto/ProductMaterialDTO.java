package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMaterialDTO {

    private Long id;
    private String productCode;
    private String productName;
    private String productGroupName;
}
