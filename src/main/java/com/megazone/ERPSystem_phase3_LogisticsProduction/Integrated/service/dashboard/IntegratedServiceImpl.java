package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service.dashboard;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto.DashboardDataDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.repository.dashboard.EnvironmentalCertificationAssessmentRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.repository.dashboard.RecentActivityRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository.basic_information_management.Employee.EmployeeRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.inventory_management.inventory.InventoryService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.work_performance.work_report.WorkPerformance;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.work_performance.work_report.WorkPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class IntegratedServiceImpl implements IntegratedService {

    private final RecentActivityRepository recentActivityRepository;
    private final EnvironmentalCertificationAssessmentRepository environmentalCertificationAssessmentRepository;
    private final WorkPerformanceRepository workPerformanceRepository;
    private final EmployeeRepository employeeRepory;
    private final InventoryService inventoryService;

    @Override
    public DashboardDataDTO dashboard() {

        
        List<DashboardDataDTO.ActivityDTO> activities = getActivityDTOS(); // 최근 활동        
        DashboardDataDTO.EnvironmentalScoreDTO environmentalScore = getEnvironmentalScoreDTO(); // 환경 점수
//        List<DashboardDataDTO.SalesDataDTO> salesDataList = getSalesDataDTOS(); // 매출 및 비용 추이 데이터 가공
        DashboardDataDTO.DashboardWidgetDTO widgets = getDashboardWidgetDTO(); // 총매출, 총직원수, 재고현황, 생산량

        return DashboardDataDTO.builder()
                .widgets(widgets)
                .activities(activities)
                .environmentalScore(environmentalScore)
//                .salesData(salesDataList)
                .build();
    }

    private DashboardDataDTO.DashboardWidgetDTO getDashboardWidgetDTO() {

        BigDecimal totalWorkPerformance = workPerformanceRepository.findAll().stream().map(WorkPerformance::getAcceptableQuantity).reduce(BigDecimal.ZERO, BigDecimal::add); // 총 생산량

        DashboardDataDTO.DashboardWidgetDTO widgets = DashboardDataDTO.DashboardWidgetDTO.builder()
                .financeName("총 매출")
                .productionName("총 생산량")
                .productionValue(totalWorkPerformance)
                .hrName("총 직원수")
                .logisticsName("총 재고 현황")
                .logisticsValue(inventoryService.allInventoryCount())
                .hrValue(Long.valueOf((employeeRepory.findAll().size())))
                .build();
        return widgets;
    }

//    @NotNull
//    private List<DashboardDataDTO.SalesDataDTO> getSalesDataDTOS() {
//        AtomicInteger monthIndex = new AtomicInteger(1);
//        List<DashboardDataDTO.SalesDataDTO> salesDataList = incomeStatementLedgerDashBoardDTO.getIncomeStatementLedger().stream()
//                .map(incomeStatementLedgerMonthList -> {
//                    BigDecimal sales = incomeStatementLedgerMonthList.stream()
//                            .filter(item -> item.getName().equals("매출"))
//                            .map(IncomeStatementLedgerShowDTO::getTotalAmount)
//                            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//                    BigDecimal cost = incomeStatementLedgerMonthList.stream()
//                            .filter(item -> item.getName().equals("비용"))
//                            .map(IncomeStatementLedgerShowDTO::getTotalAmount)
//                            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//                    return DashboardDataDTO.SalesDataDTO.builder()
//                            .name(monthIndex.getAndIncrement() + "월")
//                            .sales(sales)
//                            .cost(cost)
//                            .build();
//                })
//                .collect(Collectors.toList());
//        return salesDataList;
//    }

    @Nullable
    private DashboardDataDTO.EnvironmentalScoreDTO getEnvironmentalScoreDTO() {
        DashboardDataDTO.EnvironmentalScoreDTO environmentalScore = environmentalCertificationAssessmentRepository.findByMonth(YearMonth.now())
                .map(environmentalCertificationAssessment -> DashboardDataDTO.EnvironmentalScoreDTO.builder()
                        .totalScore(environmentalCertificationAssessment.getTotalScore())
                        .wasteScore(environmentalCertificationAssessment.getWasteScore())
                        .energyScore(environmentalCertificationAssessment.getEnergyScore())
                        .build())
                .orElse(null);
        return environmentalScore;
    }

    @NotNull
    private List<DashboardDataDTO.ActivityDTO> getActivityDTOS() {
        List<DashboardDataDTO.ActivityDTO> activities = recentActivityRepository.findAllByOrderByActivityTimeDesc()
                .stream()
                .map(activity -> DashboardDataDTO.ActivityDTO.builder()
                        .id(activity.getId())
                        .activityDescription(activity.getActivityDescription())
                        .activityType(activity.getActivityType())
                        .activityTime(calculateTimeAgo(activity.getActivityTime()))
                        .build())
                .toList();
        return activities;
    }

    private String calculateTimeAgo(LocalDateTime activityTime) {
        LocalDateTime now = LocalDateTime.now();
        long years = ChronoUnit.YEARS.between(activityTime, now);
        if (years > 0) return years + "년 전";

        long months = ChronoUnit.MONTHS.between(activityTime, now);
        if (months > 0) return months + "달 전";

        long weeks = ChronoUnit.WEEKS.between(activityTime, now);
        if (weeks > 0) return weeks + "주 전";

        long days = ChronoUnit.DAYS.between(activityTime, now);
        if (days > 0) return days + "일 전";

        long hours = ChronoUnit.HOURS.between(activityTime, now);
        if (hours > 0) return hours + "시간 전";

        long minutes = ChronoUnit.MINUTES.between(activityTime, now);
        if (minutes > 0) return minutes + "분 전";

        return "방금 전";
    }
}