package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory_inspection;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.inventory_management.inventory_adjustment.dto.InventoryInspectionStatusResponseListDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.inventory_management.inventory_inspection.InventoryInspectionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryInspectionDetailServiceImpl implements InventoryInspectionDetailService {

    private final InventoryInspectionDetailRepository inventoryInspectionDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryInspectionStatusResponseListDTO> getInspectionStatusByDateRange(LocalDate startDate, LocalDate endDate) {
        return inventoryInspectionDetailRepository.findByInspectionDateBetween(startDate, endDate).stream()
                .map(InventoryInspectionStatusResponseListDTO::mapToDTO)
                .toList();
    }

}
