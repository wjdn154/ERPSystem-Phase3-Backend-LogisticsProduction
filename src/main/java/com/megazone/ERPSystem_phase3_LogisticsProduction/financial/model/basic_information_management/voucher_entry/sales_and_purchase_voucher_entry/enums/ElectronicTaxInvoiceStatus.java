package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.voucher_entry.sales_and_purchase_voucher_entry.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum ElectronicTaxInvoiceStatus {
    PUBLISHED("Published"), // 발행
    UNPUBLISHED("Unpublished"); // 미발행


    private final String name;

    ElectronicTaxInvoiceStatus(String name) {
        this.name = name;
    }

    @JsonCreator
    public static ElectronicTaxInvoiceStatus of(String progress) {
        return Arrays.stream(ElectronicTaxInvoiceStatus.values())
                .filter(i -> i.name.equals(progress))
                .findAny()
                .orElse(null);
    }
}
