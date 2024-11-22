package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.equipment;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.equipment.MaintenanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceHistoryRepository
        extends JpaRepository<MaintenanceHistory, Long>, MaintenanceHistoryRepositoryCustom {

        List<MaintenanceHistory> findAllByOrderByMaintenanceDateDesc();

}
