package com.pergamon.application.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.pergamon.core.interfaces.IHealthCheckService;

@Component
public class HealthCheckServiceImpl implements HealthIndicator, IHealthCheckService{

	@Override
    public Health health() {
        boolean isHealthy = checkCustomService(); 
        
        if (!isHealthy) {
            return Health.down()
                    .withDetail("Error", "service null response")
                    .build();
        }
        return Health.up()
                .withDetail("Message", "ok")
                .build();
    }

    private boolean checkCustomService() {
        
        return true; 
    }
}
