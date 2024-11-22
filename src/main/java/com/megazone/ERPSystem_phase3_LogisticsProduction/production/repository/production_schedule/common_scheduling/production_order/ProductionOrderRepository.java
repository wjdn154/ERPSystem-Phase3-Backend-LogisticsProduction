package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.common_scheduling.production_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.workcenter.Workcenter;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long>, ProductionOrderRepositoryCustom {

    List<ProductionOrder> findByMpsId(Long mpsId);


    List<ProductionOrder> findByWorkcenter(Workcenter workcenter);

    List<ProductionOrder> findByConfirmedFalse();
}
