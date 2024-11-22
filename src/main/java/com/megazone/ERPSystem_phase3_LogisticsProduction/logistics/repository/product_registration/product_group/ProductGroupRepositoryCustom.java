package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.product_registration.product_group;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.ProductGroup;

import java.util.List;

public interface ProductGroupRepositoryCustom {

    List<ProductGroup> findProductGroupsBySearchTerm(String searchTerm);

}