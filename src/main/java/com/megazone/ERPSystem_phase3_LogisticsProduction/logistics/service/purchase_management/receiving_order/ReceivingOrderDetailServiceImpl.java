package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.receiving_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.receiving_processing_management.dto.ReceivingOrderDetailResponseDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.receiving_order.ReceivingOrderDetailRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.receiving_order.ReceivingOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReceivingOrderDetailServiceImpl implements ReceivingOrderDetailService {

    private final ReceivingOrderDetailRepository receivingOrderDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReceivingOrderDetailResponseDTO> getReceivingOrderDetailsWithWaitingQuantityByDateRange(LocalDate startDate, LocalDate endDate) {
        return receivingOrderDetailRepository.findWaitingForReceiptDetailsByDateRange(startDate, endDate);
    }
}
