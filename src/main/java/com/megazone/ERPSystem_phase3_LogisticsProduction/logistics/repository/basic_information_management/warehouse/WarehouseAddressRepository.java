package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.basic_information_management.warehouse;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseAddressRepository extends JpaRepository<Address, Long> {
}
