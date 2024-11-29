package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.common_scheduling.ProductionOrder;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.ProductionOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionCalculatorDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionOrderDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.Mps;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.WorkPerformanceUpdateDTO;

import java.math.BigDecimal;
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

    // 폐기물 발생량 점수 계산 함수
    Integer calculateWasteScore(BigDecimal actualWaste, BigDecimal averageWaste);

    // 에너지 사용량 점수 계산 함수
    Integer calculateEnergyScore(BigDecimal actualEnergy, BigDecimal averageEnergy);

    CalculatorResponseDTO scoreCalculatorAll(CalculatorDTO dto);
}
