package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.common_scheduling.ProductionOrder;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.ProductionOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionOrderDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.Mps;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.WorkPerformanceUpdateDTO;

import java.util.List;

public interface ProductionOrderService {
    ProductionOrderDTO getProductionOrderById(Long productionOrderId);
    List<ProductionOrderDTO> getAllProductionOrders();
    List<ProductionOrderDTO> getUnconfirmedProductionOrders();
    ProductionOrderDTO saveProductionOrder(ProductionOrderDTO productionOrderDTO);
    ProductionOrderDTO updateProductionOrder(Long productionOrderId, ProductionOrderDTO productionOrderDTO);
    void deleteProductionOrder(Long productionOrderId);
    void assignWorkersToWorkcenter(ProductionOrderDTO productionOrderDTO, ProductionOrder productionOrder);
    void updateOrderClosure(WorkPerformanceUpdateDTO dto);
    void createOrdersFromMps(Mps savedMps);
    boolean isProductionOrderConfirmed(Long id);
}
