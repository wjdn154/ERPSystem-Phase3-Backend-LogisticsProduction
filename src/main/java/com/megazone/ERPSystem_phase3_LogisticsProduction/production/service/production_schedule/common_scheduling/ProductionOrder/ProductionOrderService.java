package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.common_scheduling.ProductionOrder;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.ProductionOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.WorkerAssignment;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionCalculatorDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionOrderDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.Mps;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.WorkPerformanceUpdateDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProductionOrderService {
    ProductionOrderDTO getProductionOrderById(Long productionOrderId);
    List<ProductionOrderDTO> getAllProductionOrders();
    List<ProductionOrderDTO> getUnconfirmedProductionOrders();
    ProductionOrderDTO saveProductionOrder(ProductionOrderDTO productionOrderDTO);

    WorkerAssignment assignWorkerToShift(Long workerId, String workcenterCode, Long shiftTypeId, LocalDate assignmentDate, ProductionOrder productionOrder);

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

    // 폐기물 발생량 계산 함수
    BigDecimal calculateWaste(ProductionOrder productionOrder, BigDecimal workQuantity);

    // 실제 에너지 사용량 계산 함수
    BigDecimal calculateActualEnergy(ProductionOrder productionOrder, BigDecimal workHours);

    // 산업 평균 에너지 사용량 계산 함수
    BigDecimal calculateAverageEnergy(ProductionOrder productionOrder, BigDecimal workQuantity);

    // DTO를 엔티티로 변환
    ProductionOrder convertToEntity(ProductionOrderDTO productionOrderDTO);
}
