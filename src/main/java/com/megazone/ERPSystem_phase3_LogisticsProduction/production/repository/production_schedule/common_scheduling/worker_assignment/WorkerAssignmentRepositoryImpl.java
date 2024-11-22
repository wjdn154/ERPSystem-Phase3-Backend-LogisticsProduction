package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.common_scheduling.worker_assignment;

import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.WorkerAssignment;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.QWorkerAssignment.workerAssignment;

@Repository
@RequiredArgsConstructor
public class WorkerAssignmentRepositoryImpl implements WorkerAssignmentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tuple> findWorkerCountByWorkcenter() {
        return queryFactory
                .select(workerAssignment.workcenter.code, workerAssignment.workcenter.name, workerAssignment.count())
                .from(workerAssignment)
                .groupBy(workerAssignment.workcenter.code, workerAssignment.workcenter.name)
                .fetch();
    }

    @Override
    public List<WorkerAssignment> findAllByWorkcenterCode(String workcenterCode) {
        return queryFactory
                .selectFrom(workerAssignment)
                .where(workerAssignment.workcenter.code.eq(workcenterCode))
                .fetch();
    }

    @Override
    public List<WorkerAssignment> findAssignmentsByWorkerAndProductionOrderAndDate(Long workerId, Long productionOrderId, LocalDate assignmentDate) {
        return queryFactory
                .selectFrom(workerAssignment)
                .where(workerAssignment.worker.id.eq(workerId)
                        .and(workerAssignment.productionOrder.id.eq(productionOrderId))
                        .and(workerAssignment.assignmentDate.eq(assignmentDate)))
                .fetch();
    }

    @Override
    public boolean existsByWorkerIdAndAssignmentDate(Long workerId, LocalDate date) {
        Integer count = queryFactory
                .selectOne()
                .from(workerAssignment)
                .where(workerAssignment.worker.id.eq(workerId)
                        .and(workerAssignment.assignmentDate.eq(date)))
                .fetchFirst();
        return count != null;
    }

    @Override
    public List<WorkerAssignment> getWorkerAssignments(String workcenterCode, Optional<LocalDate> optionalDate) {
        var query = queryFactory
                .selectFrom(workerAssignment)
                .where(workerAssignment.workcenter.code.eq(workcenterCode));

        optionalDate.ifPresent(date -> query.where(workerAssignment.assignmentDate.eq(date)));

        return query.fetch();
    }

    @Override
    public List<WorkerAssignment> findWorkerAssignmentsByWorkcenterCodeAndDate(String workcenterCode, LocalDate date) {
        return queryFactory
                .selectFrom(workerAssignment)
                .where(workerAssignment.workcenter.code.eq(workcenterCode)
                        .and(workerAssignment.assignmentDate.eq(date)))
                .fetch();
    }

    @Override
    public List<WorkerAssignment> findByAssignmentDate(LocalDate currentDate) {
        return queryFactory
                .selectFrom(workerAssignment)
                .leftJoin(workerAssignment.worker).fetchJoin()
                .leftJoin(workerAssignment.shiftType).fetchJoin()
                .leftJoin(workerAssignment.productionOrder).fetchJoin()
                .where(workerAssignment.assignmentDate.eq(currentDate))
                .fetch();
    }

//    @Override
//    public List<WorkerAssignmentDTO> findTodayWorkers(String code) {
//        return queryFactory
//                .select(Projections.constructor(WorkerAssignmentDTO.class,
//                        workerAssignment.worker.id, // Long id
//                        workerAssignment.worker.employee.lastName.concat(" ").concat(workerAssignment.worker.employee.firstName), // String workerName
//                        workerAssignment.worker.employee.employeeNumber, // String employeeNumber
//                        workerAssignment.workcenter.code, // String workcenterCode
//                        workerAssignment.assignmentDate, // LocalDate assignmentDate
//                        workerAssignment.shiftType.id, // Long shiftTypeId
//                        workerAssignment.productionOrder.id)) // Long productionOrderId
//                .from(workerAssignment)
//                .where(workerAssignment.workcenter.code.eq(code)
//                        .and(workerAssignment.assignmentDate.eq(LocalDate.now())))
//                .fetch();
//    }



//    @Override
//    public Optional<WorkerAssignment> findByWorkerIdAndAssignmentDate(Long workerId, LocalDate assignmentDate) {
//        WorkerAssignment assignment = queryFactory
//                .selectFrom(workerAssignment)
//                .where(workerAssignment.worker.id.eq(workerId)
//                        .and(workerAssignment.assignmentDate.eq(assignmentDate)))
//                .fetchFirst();
//        return Optional.ofNullable(assignment);
//    }
}
