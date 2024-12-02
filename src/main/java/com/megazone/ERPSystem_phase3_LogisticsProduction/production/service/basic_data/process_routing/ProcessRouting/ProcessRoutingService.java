package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.basic_data.process_routing.ProcessRouting;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto.ProductDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.RoutingStep;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.dto.ProcessRoutingDetailDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto.ProductDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.ProcessRouting;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.dto.ProcessDetailsDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.dto.ProcessRoutingDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.dto.RoutingStepDTO;

import java.util.List;

public interface ProcessRoutingService {

//    List<ProductionRoutingDTO> getAllProductionRoutings();
//    ProductionRoutingDTO getProductionRoutingById(Long id);
//    List<ProductionRoutingDTO> searchProductionRoutings(String code, String name, Boolean isActive);

    // Routing 생성 시 RoutingStep 순서 지정해서 등록
//    ProductionRouting createProductionRoutingWithSteps(ProductionRoutingDTO routingDTO, List<RoutingStepDTO> stepDTOs);
    ProcessRoutingDTO createProcessRoutingWithSteps(ProcessRoutingDTO routingDTO);

    Product getProductByCodeOrName(ProductDetailDto productDetailDto);

    ProductDto getProductById(Long id);

    // ProductionRouting 업데이트 로직
    ProcessRoutingDetailDTO updateProcessRouting(ProcessRoutingDetailDTO processRoutingDTO);

    // ProductionRouting 삭제 로직
    ProcessRoutingDTO deleteProcessRouting(Long id);

    ProcessRoutingDTO convertToDTO(ProcessRouting processRouting);

    // Search
    List<ProcessDetailsDTO> searchProcessDetails();
    List<ProductDto> searchProducts(String keyword);


    Long getProcessIdByCodeOrName(ProcessDetailsDTO processDetailsDTO);

    //    ProductDetailDto getProductById(Long id);
    ProcessDetailsDTO getProcessDetailsById(Long id);

    ProcessRoutingDetailDTO getProcessRoutingById(Long id);

    RoutingStep convertDTOToRoutingStep(RoutingStepDTO dto, ProcessRouting processRouting);
}
