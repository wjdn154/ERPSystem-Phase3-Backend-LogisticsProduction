package com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO {
    private Long bankId;
    private String name;
    private String code;
    private String accountNumber;

    public static BankAccountDTO create(Long id, String name, String code, String accountNumber) {
        return new BankAccountDTO(id, name, code, accountNumber);
    }
}
