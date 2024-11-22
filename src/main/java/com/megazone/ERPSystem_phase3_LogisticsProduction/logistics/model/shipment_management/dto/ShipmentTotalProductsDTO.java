package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.shipment_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentTotalProductsDTO {
    private List<ShipmentProductListResponseDTO> shipmentProductListResponseDTOList;
    private Long totalQuantity;
}
