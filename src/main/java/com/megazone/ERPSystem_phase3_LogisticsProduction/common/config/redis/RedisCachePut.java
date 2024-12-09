package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisCachePut {
    String cacheName();
    String key() default "";
}
