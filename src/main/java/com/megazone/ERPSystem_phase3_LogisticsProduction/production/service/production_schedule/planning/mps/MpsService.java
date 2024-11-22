package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.planning.mps;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.dto.MpsDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.dto.searchMpsDTO;

import java.time.LocalDate;
import java.util.List;

public interface MpsService {
    MpsDTO saveMps(MpsDTO dto);

    List<searchMpsDTO> searchMps(LocalDate date);

    MpsDTO getMpsById(Long id);

    MpsDTO updateMps(Long id, MpsDTO dto);

    String deleteMps(Long id);

    void updateMpsStatusBasedOnOrders(Long mpsId);

    MpsDTO confirmMps(Long mpsId);


//    void createMps(ProductionRequest request);
}
