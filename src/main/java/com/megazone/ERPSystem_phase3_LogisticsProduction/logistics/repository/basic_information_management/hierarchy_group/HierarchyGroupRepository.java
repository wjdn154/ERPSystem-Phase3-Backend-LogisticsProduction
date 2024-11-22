package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.basic_information_management.hierarchy_group;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.hierarchy_group.HierarchyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HierarchyGroupRepository extends JpaRepository<HierarchyGroup, Long>, HierarchyGroupRepositoryCustom {
    Optional<HierarchyGroup> findByHierarchyGroupCode(String code);

    List<HierarchyGroup> findByParentGroupIsNull();
}
