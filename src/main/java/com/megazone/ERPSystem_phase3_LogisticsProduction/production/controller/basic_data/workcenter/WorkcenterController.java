package com.megazone.ERPSystem_phase3_LogisticsProduction.production.controller.basic_data.workcenter;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.dto.WarehouseResponseDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.workcenter.dto.WorkcenterDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.WorkerAssignmentDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.dto.ProcessDetailsDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.equipment.dto.EquipmentDataDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.basic_data.workcenter.WorkcenterService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.basic_data.process_routing.ProcessDetails.ProcessDetailsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/production/workcenters")
@RequiredArgsConstructor
public class WorkcenterController {

    private final WorkcenterService workcenterService;
    private final ProcessDetailsService processDetailsService;
    // 1. 전체 작업장 조회
    @PostMapping("")
    public ResponseEntity<List<WorkcenterDTO>> getAllWorkcenters() {

        List<WorkcenterDTO> workcenterDTOs = workcenterService.findAll();
        return ResponseEntity.ok(workcenterDTOs);
    }


//    // 2. 이름으로 작업장 리스트 검색 조회
//    @PostMapping("/search")
//    public ResponseEntity<List<WorkcenterDTO>> getWorkcentersByName(
//            @RequestParam("name") String name) {
//
//        Users user = usersRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
//        Long company_id = user.getCompany().getId();
//
//        List<WorkcenterDTO> workcenterDTOs = workcenterService.findByNameContaining(company_id, name);
//        return ResponseEntity.ok(workcenterDTOs);
//    }

    // 3. 코드로 작업장 세부 정보 조회
    @PostMapping("/details/{code}")
    public ResponseEntity<WorkcenterDTO> getWorkcenterDetailByCode(
            @PathVariable("code") String code) {

        Optional<WorkcenterDTO> workcenterDTO = workcenterService.findByCode(code);

        return workcenterDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveWorkcenter(@RequestBody WorkcenterDTO workcenterDTO) {

        try {
            workcenterService.save(workcenterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("작업장이 성공적으로 생성되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());             // 중복 코드 발생 시 메시지 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("작업장을 생성하는 중 오류가 발생했습니다.");
        }
    }


    // 5. 코드로 수정
    @PostMapping("/update/{code}")
    public ResponseEntity<String> updateWorkcenter(
            @PathVariable("code") String code,
            @RequestBody WorkcenterDTO workcenterDTO) {

        try {
            Optional<WorkcenterDTO> updatedWorkcenterDTO = workcenterService.updateByCode(code, workcenterDTO);
            if (updatedWorkcenterDTO.isPresent()) {
                return ResponseEntity.ok("작업장이 성공적으로 업데이트되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("작업장을 찾을 수 없습니다.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("작업장을 업데이트하는 중 오류가 발생했습니다.");
        }
    }


    // 6. 코드 매칭 삭제
    @PostMapping("/delete")
    public ResponseEntity<String> deleteWorkcenter(@RequestParam("code") String code) {

        try {
            workcenterService.deleteByCode(code);
            return ResponseEntity.ok("작업장이 성공적으로 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제할 수 없습니다: " + e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("작업장을 찾을 수 없습니다: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("예상치 못한 오류가 발생했습니다.");
        }
    }

    // 7. 작업장 관리 내에서 공장 리스트 조회
    @PostMapping("/factories")
    public ResponseEntity<List<WarehouseResponseDTO>> getWorkcenterFactories() {

        List<WarehouseResponseDTO> factoryDTOs = workcenterService.findAllFactories();
        return ResponseEntity.ok(factoryDTOs);
    }

    /**
     * Client에서 각 별도 API로 호출해서 처리 시, 아래 삭제
     */

    // 8-1. 생산 공정 전체 조회
    @PostMapping("/processes")
    public ResponseEntity<List<ProcessDetailsDTO>> getAllProcessDetails() {

        List<ProcessDetailsDTO> processDetailsDTOs = processDetailsService.getAllProcessDetails();
        return ResponseEntity.ok(processDetailsDTOs);
    }

    // 8. 생산 공정 정보 조회
    @PostMapping("/processes/{processCode}")
    public ResponseEntity<ProcessDetailsDTO> getProcessByCode(@PathVariable("processCode") String processCode) {

        Optional<ProcessDetailsDTO> processDetailsDTO = processDetailsService.getProcessDetailsByCode(processCode);
        return processDetailsDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/equipments/byWorkcenter/{workcenterCode}")
    public ResponseEntity<List<EquipmentDataDTO>> getEquipmentsByWorkcenterCode(
            @PathVariable("workcenterCode") String workcenterCode) {

        List<EquipmentDataDTO> equipmentDataDTOs =
                workcenterService.findEquipmentByWorkcenterCode(workcenterCode);
        return ResponseEntity.ok(equipmentDataDTOs);
    }

    // 10. 작업자 배정 이력 조회
    @PostMapping("/workerAssignments/{workcenterCode}")
    public ResponseEntity<List<WorkerAssignmentDTO>> getWorkerAssignmentsByWorkcenterCode(@PathVariable("workcenterCode") String workcenterCode) {

       List<WorkerAssignmentDTO> workerAssignmentDTOs = workcenterService.findWorkerAssignmentsByWorkcenterCode(workcenterCode);
        return ResponseEntity.ok(workerAssignmentDTOs);
    }
}

