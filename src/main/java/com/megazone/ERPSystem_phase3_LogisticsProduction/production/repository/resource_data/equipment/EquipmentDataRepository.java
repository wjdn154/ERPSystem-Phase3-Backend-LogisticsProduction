package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.equipment;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.equipment.EquipmentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentDataRepository extends JpaRepository<EquipmentData, Long> , EquipmentDataRepositoryCustom{

    boolean existsByEquipmentNum(String equipmentNum);

    Optional<EquipmentData> findByEquipmentNum(String equipmentNum);

    List<EquipmentData> findAllByOrderByPurchaseDateDesc();

    List<EquipmentData> findByWorkcenterId(Long workcenterId);

}
