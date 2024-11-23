package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VatAmountWithSupplyAmountDTO {
    private Long vatTypeId;
    private BigDecimal supplyAmount;
}
