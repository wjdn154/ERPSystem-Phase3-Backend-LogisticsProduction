package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.common_scheduling.shift_type;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShiftTypeRepository extends JpaRepository<ShiftType, Long>, ShiftTypeRepositoryCustom {
    Optional<ShiftType> findById(Long shiftTypeId);

    Optional<ShiftType> findByName(String shift);

    Boolean findIsUsedById(Long id);
}
