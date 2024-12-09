package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.aop;


import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis.RedisCachePut;
import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis.RedisCacheable;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
@RequiredArgsConstructor
public class RedisCacheAspect {

    private final RedisTemplate<String, Object> redisTemplate;

    // Read Through
    @Around("@annotation(com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis.RedisCacheable)")
    public Object cacheableProcess(ProceedingJoinPoint joinPoint) throws Throwable {

        RedisCacheable redisCacheable =  getCacheable(joinPoint);
        final String cacheKey = generateKey(redisCacheable.cacheName(),joinPoint);

        // 캐시 확인
        if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
            return redisTemplate.opsForValue().get(cacheKey); // Cache Hit
        }

        // Cache Miss: 데이터베이스 조회
        final Object methodReturnValue = joinPoint.proceed();
        final long cacheTTL = redisCacheable.expireTime();
        if (cacheTTL < 0) {
            redisTemplate.opsForValue().set(cacheKey,methodReturnValue);
        } else {
            redisTemplate.opsForValue().set(cacheKey,methodReturnValue,cacheTTL, TimeUnit.SECONDS);
        }

        return methodReturnValue;
    }

    // Write Through
    @Around("@annotation(com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis.RedisCachePut)")
    public Object cachePutProcess(ProceedingJoinPoint joinPoint) throws Throwable {

        // 캐싱 대상 메서드 실행
        final Object methodReturnValue = joinPoint.proceed();

        // RedisCachePut 어노테이션 정보 가져오기
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedisCachePut redisCachePut = AnnotationUtils.getAnnotation(method, RedisCachePut.class);

        if (redisCachePut != null) {
            // 캐시 키 생성
            String cacheKey = generateKey(redisCachePut.cacheName(), joinPoint);

            // Redis 캐시에 데이터 저장
            redisTemplate.opsForValue().set(cacheKey, methodReturnValue);
            System.out.println("Write Through: Cache updated for key: " + cacheKey);
        }

        return methodReturnValue;
    }

    private RedisCacheable getCacheable(ProceedingJoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();

        return AnnotationUtils.getAnnotation(method, RedisCacheable.class);
    }

    private String generateKey(String cacheName, ProceedingJoinPoint joinPoint) {

        String generatedKey = StringUtils.arrayToCommaDelimitedString(joinPoint.getArgs());

        return String.format("%s:%s", cacheName, generatedKey);
    }
}
