package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.planning.mrp;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.planning.dto.MrpDTO;

import java.util.List;

public interface MrpService {
//    void generateMrpForMps(Mps mps);

    MrpDTO createMrp(MrpDTO mrpDto);

    MrpDTO getMrpById(Long id);

    List<MrpDTO> getAllMrps();

    MrpDTO updateMrp(Long id, MrpDTO mrpDto);

    void deleteMrp(Long id);

}
