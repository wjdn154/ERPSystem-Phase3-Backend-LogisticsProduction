package com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessRoutingDetail2DTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private boolean standard;
    private boolean active;

    private List<ProcessDetailsDTO> processDetails;
    private List<ProductDto> products;
}
