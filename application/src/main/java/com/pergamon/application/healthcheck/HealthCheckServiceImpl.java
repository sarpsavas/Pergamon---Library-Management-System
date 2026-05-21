package com.pergamon.application.healthcheck;

import java.sql.Connection;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.pergamon.core.interfaces.IHealthCheckService;
import javax.sql.DataSource;

@Component
public class HealthCheckServiceImpl implements HealthIndicator, IHealthCheckService{

	private final DataSource dataSource;
	
	public HealthCheckServiceImpl(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
    public Health health() {
        boolean dbHealty = isDatabaseConnected(); 
        
        if (!dbHealty) {
            return Health.down()
                    .withDetail("Error", "db not connect")
                    .build();
        }
        return Health.up()
                .withDetail("Message", "ok")
                .build();
    }

	private boolean isDatabaseConnected() {
        try (Connection connection = dataSource.getConnection()) {
            
            return connection.isValid(2); // 2 saniye timeout
        } catch (Exception e) {
            return false;
        }
    }
}
