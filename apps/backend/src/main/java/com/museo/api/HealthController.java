package com.museo.api;

import org.jspecify.annotations.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public Map<String, String> getHealth() {
        return Map.of(
            "status", "UP",
            "version", "0.0.1-SNAPSHOT",
            "message", "Museo Backend is healthy"
        );
    }
}
