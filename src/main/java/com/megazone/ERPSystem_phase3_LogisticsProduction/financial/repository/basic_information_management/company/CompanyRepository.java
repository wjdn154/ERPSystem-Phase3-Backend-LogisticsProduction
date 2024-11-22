package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.repository.basic_information_management.company;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}