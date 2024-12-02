package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.production_schedule.common_scheduling.ShiftType;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.ShiftTypeDTO;

import java.util.List;

public interface ShiftTypeService {
    List<ShiftTypeDTO> getAllShiftTypes();
    ShiftTypeDTO getShiftTypeById(Long id);
    ShiftTypeDTO createShiftType(ShiftTypeDTO shiftTypeDTO);
    ShiftTypeDTO updateShiftType(ShiftTypeDTO shiftTypeDTO);
    void deleteShiftType(Long id);

    // 교대 유형이 사용 중인지 확인하는 메서드
    boolean shiftTypeInUse(Long id);
}
