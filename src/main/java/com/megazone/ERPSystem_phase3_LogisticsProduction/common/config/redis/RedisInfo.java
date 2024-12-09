package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis;

import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.data.redis")
@Configuration
public class RedisInfo {
//    private List<String> clusterNodes;
//    private String password;
//    private String readFrom = "REPLICA_PREFERRED";
//    private String clientName;
//    private RedisInfo cluster;

    private String host; // Redis 호스트
    private int port; // Redis 포트
    private String password; // Redis 비밀번호
    private String clientName = "default-client"; // 클라이언트 이름

}
