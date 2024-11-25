package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.resource_data.worker;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.RecentActivity;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.enums.ActivityType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.NotificationType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.PermissionType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.repository.dashboard.RecentActivityRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service.notification.NotificationService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository.basic_information_management.Employee.EmployeeRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.common_scheduling.WorkerAssignment;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.production_schedule.dto.WorkerAssignmentDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.Worker;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.resource_data.dto.*;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.production_schedule.common_scheduling.worker_assignment.WorkerAssignmentRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.resource_data.worker.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {


    private final WorkerRepository workerRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkerAssignmentRepository workerAssignmentRepository;
    private final RecentActivityRepository recentActivityRepository;
    private final NotificationService notificationService;

    //작업자 상세 정보 수정(교육 이수 여부만 등록 가능)
    @Override
    public Optional<WorkerDetailShowDTO> saveWorker(Long companyId, WorkerDetailShowDTO dto) {
        return Optional.empty();
    }

    //작업자 상세 정보 수정(교육 이수 여부만 수정 가능)
    @Override
    public Optional<WorkerDetailShowDTO> updateWorker(Long id, WorkerDetailShowDTO dto) {

        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 조회할 수 없습니다." + id));

        //새로 들어온 dto를 기존 엔티티에 업데이트. 교육이수여부만 업데이트 나머지는 기존값 그대로.
        worker.setTrainingStatus(dto.getTrainingStatus());

        Worker updateWorker = workerRepository.save(worker);

        recentActivityRepository.save(RecentActivity.builder()
                .activityDescription("작업자 교육이수여부 1건 변경")
                .activityType(ActivityType.PRODUCTION)
                .activityTime(LocalDateTime.now())
                .build());


        notificationService.createAndSendNotification(
                ModuleType.PRODUCTION,
                PermissionType.ALL,
                "작업자 교육이수여부 1건이 변경되었습니다.",
                NotificationType.UPDATE_WORKER);

        WorkerDetailShowDTO workerDetailUpdateDTO = workerToDTO(updateWorker);

        return Optional.of(workerDetailUpdateDTO);
    }

    //생산에 해당하는 작업자 목록 조회
    @Override
    public List<ListWorkerDTO> findAllWorker() {

        //해당 회사아이디에 해당하는 부서 작업자 목록 조회
        List<ListWorkerDTO> workerList = workerRepository.findAllWorkerByDepartment();


        return workerList;
    }

    //해당 작업자 상세 정보 조회
    @Override
    public Optional<WorkerDetailShowDTO> findWorkerById(Long id) {

        //아이디에 해당하는 작업자 엔티티 조회
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디를 조회할 수 없습니다." +id));

        //엔티티를 dto로 반환
        WorkerDetailShowDTO workerDetailShowDTO = workerToDTO(worker);

        //dto 반환
        return Optional.of(workerDetailShowDTO);
    }


    //해당 작업자의 모든 작업 배치 이력 조회
    @Override
    public ListWorkerAttendanceDTO findAllWorkerById(Long id) {

        //worker id로 worker 조회
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(("작업자를 찾을 수 없습니다.") + id));

//        List<Attendance> attendances = attendanceRepository.findByEmployee(worker.getEmployee());

        //근태 관리 리스트 생성
//        List<WorkerAttendanceDTO> attendanceList = attendances.stream()
//                .map(attendance -> new WorkerAttendanceDTO(
//                        attendance.getAttendanceCode(),
//                        attendance.getDate().toString(),
//                        attendance.getCheckInTime().toString(),
//                        attendance.getCheckOutTime().toString(),
//                        attendance.getStatus().toString()
//                ))
//                .sorted((a1, a2) -> a2.getAttendanceDate().compareTo(a1.getAttendanceDate()))    //날짜 기준 내림차순 정렬
//                .toList();

        List<WorkerAttendanceDTO> attendanceList = null;

// 작업 배치 관리 리스트 생성
        List<WorkerAssignmentDTO> assignmentList = worker.getWorkerAssignments().stream()
                .map(assignment -> WorkerAssignmentDTO.builder()
                        .workerName(assignment.getWorker().getEmployee().getFirstName() + " " + assignment.getWorker().getEmployee().getLastName()) // 작업자 이름
                        .employeeNumber(assignment.getWorker().getEmployee().getEmployeeNumber()) // 직원 번호
                        .workcenterCode(assignment.getWorkcenter().getCode()) // 작업장 코드
                        .workcenterName(assignment.getWorkcenter().getName()) // 작업장 이름
                        .assignmentDate(assignment.getAssignmentDate()) // 배정 날짜
                        .shiftTypeName(assignment.getShiftType().getName()) // 교대 근무 유형
                        .productionOrderName(assignment.getProductionOrder().getName()) // 연관된 작업 지시
                        .build()
                ).toList();

        //최종 dto 생성 및 반환
        return new ListWorkerAttendanceDTO(
                worker.getId(),
                worker.getEmployee().getEmployeeNumber(),
                worker.getEmployee().getFirstName(),
                worker.getEmployee().getLastName(),
                attendanceList,
                assignmentList
        );
    }

    //worker 엔티티를 WorkerDetailShowDTO 로 변환
    private WorkerDetailShowDTO workerToDTO(Worker worker) {

        WorkerDetailShowDTO workerDetailShowDTO = new WorkerDetailShowDTO();
        workerDetailShowDTO.setId(worker.getId());
        workerDetailShowDTO.setTrainingStatus(worker.getTrainingStatus());
        workerDetailShowDTO.setEmployeeNumber(worker.getEmployee().getEmployeeNumber());
        workerDetailShowDTO.setEmployeeFirstName(worker.getEmployee().getFirstName());
        workerDetailShowDTO.setEmployeeLastName(worker.getEmployee().getLastName());
        workerDetailShowDTO.setDepartmentName(worker.getEmployee().getDepartment().getDepartmentName());
//        workerDetailShowDTO.setPositionName(worker.getEmployee().getPosition().getPositionName());
//        workerDetailShowDTO.setJobTitleName(worker.getEmployee().getJobTitle().getJobTitleName());
//        workerDetailShowDTO.setPhoneNumber(worker.getEmployee().getPhoneNumber());
//        workerDetailShowDTO.setEmploymentStatus(worker.getEmployee().getEmploymentStatus());
//        workerDetailShowDTO.setEmploymentType(worker.getEmployee().getEmploymentType());
//        workerDetailShowDTO.setHireDate(worker.getEmployee().getHireDate());
//        workerDetailShowDTO.setProfilePicture(worker.getEmployee().getImagePath());

        //작업배치가 비어있지 않다면 배치 날짜 기준으로 가장 최근의 작업배치 정보 가져오기
        if(worker.getWorkerAssignments() != null && !worker.getWorkerAssignments().isEmpty()){
            WorkerAssignment recentAssignment = worker.getWorkerAssignments().stream()
                            .max(Comparator.comparing(WorkerAssignment::getAssignmentDate))
                                    .orElseThrow(() -> new IllegalArgumentException("작업 배치 정보가 없습니다. "));
            workerDetailShowDTO.setWorkcenterCode(recentAssignment.getWorkcenter().getCode());
            workerDetailShowDTO.setWorkcenterName(recentAssignment.getWorkcenter().getName());
        }
//        List<WorkerAssignmentDTO> workerAssignmentDTOS = worker.getWorkerAssignments().stream()
//                .map(assignment -> {
//                    WorkerAssignmentDTO dto = new WorkerAssignmentDTO();
//                    dto.setId(assignment.getId());
//                    dto.setWorkcenterCode(assignment.getWorkcenter().getCode());
//                    dto.setWorkcenterName(assignment.getWorkcenter().getName());
//                    return dto;
//                })
//                .collect(Collectors.toList());

        return workerDetailShowDTO;
    }
}
