package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.aop;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class XRayTracingAspect {

    // 스레드 로컬로 메인 세그먼트 생성 여부를 확인
    private static final ThreadLocal<Boolean> mainSegmentCreated = ThreadLocal.withInitial(() -> false);

    // 서비스 Layer 메소드 호출 전후로 메인 세그먼트 생성
    @Around("execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.financial.service..*(..)) || " +
            "execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.hr.service..*(..)) || " +
            "execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service..*(..)) || " +
            "execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.production.service..*(..))")
    public Object traceServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        // 메인 세그먼트가 이미 생성된 경우 새로 생성하지 않음
        if (mainSegmentCreated.get()) {
            return joinPoint.proceed();
        }

        // 메인 세그먼트 생성
        Segment segment = AWSXRay.beginSegment("Service-" + methodName);
        mainSegmentCreated.set(true);  // 플래그 설정

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            segment.addException(e);
            throw e;
        } finally {
            AWSXRay.endSegment(); // 메인 세그먼트 종료
            mainSegmentCreated.remove(); // 트랜잭션 종료 후 플래그 초기화
        }
        return result;
    }

    // 리포지토리 Layer 메소드 호출 전후로 서브세그먼트 생성
    @Around("execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.financial.repository..*(..)) || " +
            "execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository..*(..)) || " +
            "execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository..*(..)) || " +
            "execution(* com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository..*(..))")
    public Object traceRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        // 메인 세그먼트가 없는 경우 서브세그먼트 생성 생략
        if (!AWSXRay.getCurrentSegmentOptional().isPresent()) {
            return joinPoint.proceed();
        }

        Subsegment subsegment = AWSXRay.beginSubsegment("Repository-" + methodName);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            subsegment.addException(e);
            throw e;
        } finally {
            AWSXRay.endSubsegment(); // 서브세그먼트 종료
        }
        return result;
    }
}
