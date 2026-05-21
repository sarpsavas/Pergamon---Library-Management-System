package com.pergamon.core.interfaces;

import org.springframework.boot.actuate.health.Health;

public interface IHealthCheckService {

	Health health();
}
