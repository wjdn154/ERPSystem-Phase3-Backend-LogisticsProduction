package com.megazone.ERPSystem_phase3_LogisticsProduction.financial.repository.basic_information_management.client;

import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCode(String code);
}