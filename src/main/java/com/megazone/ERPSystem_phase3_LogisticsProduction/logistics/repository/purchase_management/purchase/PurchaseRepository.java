package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase;

import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>, PurchaseRepositoryCustom {
}
