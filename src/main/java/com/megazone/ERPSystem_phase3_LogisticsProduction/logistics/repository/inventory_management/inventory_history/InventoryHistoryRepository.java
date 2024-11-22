package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.inventory_management.inventory_history;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory.InventoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory, Long>, InventoryHistoryRepositoryCustom {
    Long countByWorkDate(LocalDate transferDate);

    Long countBySlipDate(LocalDate transferDate);
}
