package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.common_scheduling.ShiftType;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ShiftTypeDTO;

import java.util.List;

public interface ShiftTypeService {
    List<ShiftTypeDTO> getAllShiftTypes();
    ShiftTypeDTO getShiftTypeById(Long id);
    ShiftTypeDTO createShiftType(ShiftTypeDTO shiftTypeDTO);
    ShiftTypeDTO updateShiftType(ShiftTypeDTO shiftTypeDTO);
    void deleteShiftType(Long id);
}
