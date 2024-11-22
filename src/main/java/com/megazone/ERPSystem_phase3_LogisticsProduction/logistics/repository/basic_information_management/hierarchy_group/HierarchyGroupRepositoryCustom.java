package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.basic_information_management.hierarchy_group;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;

import java.util.List;

public interface HierarchyGroupRepositoryCustom {
    List<Warehouse> findWarehousesByHierarchyGroupId(Long groupId);
}
