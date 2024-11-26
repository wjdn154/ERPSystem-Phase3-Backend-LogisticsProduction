package com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.worker;

import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.QDepartment;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.QEmployee;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentStatus;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.enums.EmploymentType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.QWorker;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto.ListWorkerDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.QWorker.worker;

@Repository
@RequiredArgsConstructor
public class WorkerRepositoryImpl implements WorkerRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**부서가 생산인 모든 작업자를 조회함. 작업자 목록을 반환함.
     * */
    @Override
    public List<ListWorkerDTO> findAllWorkerByDepartment() {

        QWorker worker = QWorker.worker;
        QEmployee employee = QEmployee.employee;
        QDepartment department = QDepartment.department;

        return queryFactory
                .select(Projections.constructor(ListWorkerDTO.class,     //ListWorkerDTO에 담길 필드를 명시
                        worker.id.as("id"),
                        worker.trainingStatus.stringValue().as("trainingStatus"),
                        worker.employee.id.as("employeeId"),
                        worker.employee.employeeNumber.as("employeeNumber"),
                        worker.employee.firstName.as("employeeFirstName"),
                        worker.employee.lastName.as("employeeLastName"),
                        worker.employee.department.departmentName.as("departmentName"),
                        Expressions.nullExpression(String.class),
                        Expressions.nullExpression(String.class),
                        Expressions.nullExpression(EmploymentStatus.class),
                        Expressions.nullExpression(EmploymentType.class)))
                .from(worker)
                .join(worker.employee,employee)
                .join(employee.department,department)
                .where(department.departmentName.contains("생산"))
                .orderBy(worker.employee.firstName.asc())     //오름차순으로 정렬
                .fetch();
    }

}