package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.basic_data.process_routing.RoutingStep;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoutingStepRepositoryImpl implements RoutingStepRepositoryCustom {

    private final JPAQueryFactory queryFactory;

}
