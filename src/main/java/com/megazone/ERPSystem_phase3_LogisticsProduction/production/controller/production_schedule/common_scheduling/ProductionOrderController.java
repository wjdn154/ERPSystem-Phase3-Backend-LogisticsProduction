package com.megazone.ERPSystem_phase3_LogisticsProduction.production.controller.production_schedule.common_scheduling;

import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis.RedisCacheable;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.ProductionOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.CalculatorDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.CalculatorResponseDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionCalculatorDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ProductionOrderDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.dto.WorkPerformanceUpdateDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.common_scheduling.production_order.ProductionOrderRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.common_scheduling.ProductionOrder.ProductionOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/production/productionOrder")
@RequiredArgsConstructor
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;
    private final ProductionOrderRepository productionOrderRepository;

    /**
     * 작업 지시 저장
     *
     * @param productionOrderDTO 작업 지시 정보
     * @return 생성된 작업 지시 정보
     */

    @PostMapping("/save")
    public ResponseEntity<ProductionOrderDTO> saveProductionOrder(@RequestBody ProductionOrderDTO productionOrderDTO) {
        ProductionOrderDTO savedProductionOrder = productionOrderService.saveProductionOrder(productionOrderDTO);
        return ResponseEntity.ok(savedProductionOrder);
    }

    /**
     * 작업 지시에 작업자 배정
     *
     * @param productionOrderId 작업 지시 ID
     * @param productionOrderDTO 작업 지시 정보 (작업자 배정 정보 포함)
     * @return 성공 시 200 OK
     */
    @PostMapping("/{productionOrderId}/assignWorkers")
    public ResponseEntity<String> assignWorkersToWorkcenter(@PathVariable("productionOrderId") Long productionOrderId, @RequestBody ProductionOrderDTO productionOrderDTO) {
        try {
            ProductionOrder productionOrder = productionOrderRepository.findById(productionOrderId)
                    .orElseThrow(() -> new EntityNotFoundException("작업지시를 찾을 수 없습니다."));
            productionOrderService.assignWorkersToWorkcenter(productionOrderDTO, productionOrder);

            return ResponseEntity.ok("작업자 배정이 성공적으로 완료되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("작업 지시를 찾을 수 없습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 중복 배정 검증 실패 시 에러 메시지 반환
//        } catch (Exception e) {
//            // 500 Internal Server Error에 대한 처리
//            log.error("서버 오류 발생: ", e);  // 서버 로그에 구체적인 오류 정보 기록
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
    }

    /**
     * 작업 지시 마감 여부 업데이트
     * - WorkPerformance 상태 변경에 따른 작업 지시 마감 처리
     */
    @PostMapping("/closure")
    public ResponseEntity<Object> updateOrderClosure(@RequestBody WorkPerformanceUpdateDTO dto) {
        try{
            productionOrderService.updateOrderClosure(dto);
            return ResponseEntity.status(HttpStatus.OK).body("마감처리 성공");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("마감처리 실패, 이유 : " + e.getMessage());
        }

    }


    /**
     * 모든 작업 지시 조회
     *
     * @return 모든 작업 지시 정보 리스트
     */
    @PostMapping("/all")
    public ResponseEntity<List<ProductionOrderDTO>> getAllProductionOrders() {
        List<ProductionOrderDTO> productionOrders = productionOrderService.getAllProductionOrders();
        return ResponseEntity.ok(productionOrders);
    }

    // 미확정 작업 지시 조회
    @PostMapping("/unconfirmed")
    public ResponseEntity<Object> getUnconfirmedProductionOrders() {
        try{
            List<ProductionOrderDTO> productionOrders = productionOrderService.getUnconfirmedProductionOrders();
            return ResponseEntity.ok(productionOrders);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("미확정 작업 지시 조회 중 오류 발생 : " + e.getMessage());
        }

    }



    /**
     * 작업 지시 ID로 단일 작업 지시 조회
     *
     * @param productionOrderId 작업 지시 ID
     * @return 작업 지시 정보
     */
    @PostMapping("/{productionOrderId}")
    public ResponseEntity<ProductionOrderDTO> getProductionOrderById(@PathVariable("productionOrderId") Long productionOrderId) {
        ProductionOrderDTO productionOrder = productionOrderService.getProductionOrderById(productionOrderId);
        return ResponseEntity.ok(productionOrder);
    }

    /**
     * 작업 지시 수정
     *
     * @param productionOrderId  수정할 작업 지시 ID
     * @param productionOrderDTO 수정할 작업 지시 정보
     * @return 수정된 작업 지시 정보
     */
    @PostMapping("/update/{productionOrderId}")
    public ResponseEntity<ProductionOrderDTO> updateProductionOrder(@PathVariable("productionOrderId") Long productionOrderId, @RequestBody ProductionOrderDTO productionOrderDTO) {
        ProductionOrderDTO updatedProductionOrder = productionOrderService.updateProductionOrder(productionOrderId, productionOrderDTO);
        return ResponseEntity.ok(updatedProductionOrder);
    }

    /**
     * 작업 지시 삭제
     *
     * @param productionOrderId 삭제할 작업 지시 ID
     * @return 성공 시 200 OK
     */
    @PostMapping("/delete/{productionOrderId}")
    public ResponseEntity<String> deleteProductionOrder(@PathVariable("productionOrderId") Long productionOrderId) {
        productionOrderService.deleteProductionOrder(productionOrderId);
        return ResponseEntity.ok("작업 지시가 성공적으로 삭제되었습니다.");
    }

    /**
     * 작업 지시 확정
     */
    @PostMapping("/confirm/{id}")
    public ResponseEntity<Object> confirmProductionOrder(@PathVariable("id") Long id) {
        try {
            boolean isConfirmed = productionOrderService.isProductionOrderConfirmed(id);
            return isConfirmed ? ResponseEntity.status(HttpStatus.OK).body("작업 지시가 성공적으로 확정되었습니다.") :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("작업 지시 확정에 실패했습니다.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("작업 지시 확정에 실패했습니다.");
        }
    }

    @PostMapping("/calculator/wasteScore")
    public ResponseEntity<Object> wasteScoreCalculator(@RequestBody ProductionCalculatorDTO dto) {
        try {
            Integer responseData = productionOrderService.calculateWasteScore(dto.getActualData(),dto.getAverageData());
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/calculator/energyScore")
    public ResponseEntity<Object> energyScoreCalculator(@RequestBody ProductionCalculatorDTO dto) {
        try {
            Integer responseData = productionOrderService.calculateEnergyScore(dto.getActualData(),dto.getAverageData());
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/calculator/scoreAll")
    public ResponseEntity<Object> scoreCalculator(@RequestBody CalculatorDTO dto) {
        try {
            CalculatorResponseDTO responseData = productionOrderService.scoreCalculatorAll(dto);
            return ResponseEntity.status(HttpStatus.OK).body(responseData);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

