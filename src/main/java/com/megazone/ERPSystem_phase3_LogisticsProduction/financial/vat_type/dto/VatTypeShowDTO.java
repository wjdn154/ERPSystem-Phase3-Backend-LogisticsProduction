package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.voucher_entry.sales_and_purchase_voucher_entry.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VatTypeShowDTO {
    private Long vatTypeId;
    private String vatTypeCode;
    private String vatTypeName;
    private String description;
    private TransactionType transactionType;
}
