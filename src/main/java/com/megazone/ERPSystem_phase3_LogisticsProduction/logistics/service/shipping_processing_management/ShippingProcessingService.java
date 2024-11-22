package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.shipping_processing_management;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.shipping_processing_management.dto.ShippingProcessingRequestDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.shipping_processing_management.dto.ShippingProcessingResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ShippingProcessingService {
    void registerShippingProcessing(ShippingProcessingRequestDTO requestDTO);

    List<ShippingProcessingResponseDTO> getShippingProcessingList(LocalDate startDate, LocalDate endDate);

    void processShipping(Long id);
}
