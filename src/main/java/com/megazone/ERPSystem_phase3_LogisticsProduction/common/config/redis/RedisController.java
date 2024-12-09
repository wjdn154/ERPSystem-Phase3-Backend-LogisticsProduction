package com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam String key, @RequestParam String value) {
        redisService.set(key, value);
        return ResponseEntity.ok("Saved key: " + key + " with value: " + value);
    }

    @GetMapping("/get")
    public ResponseEntity<String> get(@RequestParam String key) {
        String value = redisService.get(key);
        return value != null ? ResponseEntity.ok("Value for key " + key + ": " + value)
                : ResponseEntity.badRequest().body("Key not found");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisService.delete(key);
        return ResponseEntity.ok("Deleted key: " + key);
    }
}
