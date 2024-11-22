package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.sales_management.orders;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.sales_management.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersRepositoryCustom {
}
