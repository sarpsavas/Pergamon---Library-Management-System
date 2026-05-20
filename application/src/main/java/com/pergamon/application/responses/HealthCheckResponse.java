package com.pergamon.application.responses;

import org.springframework.boot.actuate.health.Health;

public class HealthCheckResponse 
{
	public Health health;
	
	public HealthCheckResponse(Health health)
	{
		this.health = health;
	}
}
