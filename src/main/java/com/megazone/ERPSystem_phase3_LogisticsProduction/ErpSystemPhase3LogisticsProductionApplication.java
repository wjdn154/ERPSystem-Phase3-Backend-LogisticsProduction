package com.megazone.ERPSystem_phase3_LogisticsProduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ErpSystemPhase3LogisticsProductionApplication {
	public static void main(String[] args) {

		SpringApplication.run(ErpSystemPhase3LogisticsProductionApplication.class, args);
	}
}