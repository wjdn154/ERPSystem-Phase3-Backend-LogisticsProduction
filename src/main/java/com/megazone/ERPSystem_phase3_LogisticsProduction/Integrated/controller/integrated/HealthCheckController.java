package com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.controller.integrated;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    @GetMapping("/api/health")
    public String healthCheck() {
        return "healthCheck 성공";
    }
}
