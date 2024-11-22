package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.worker;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto.ListWorkerDTO;

import java.util.List;

public interface WorkerRepositoryCustom {

    List<ListWorkerDTO> findAllWorkerByDepartment();
}
