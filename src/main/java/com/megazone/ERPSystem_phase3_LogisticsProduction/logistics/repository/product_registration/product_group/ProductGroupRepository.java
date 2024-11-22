package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.product_registration.product_group;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long>, ProductGroupRepositoryCustom {
    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, Long id);

    Optional<ProductGroup> findByName(String name);

}

