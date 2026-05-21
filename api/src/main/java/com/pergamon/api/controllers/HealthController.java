package com.pergamon.api.controllers;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.healthcheck.HealthCheckServiceImpl;
import com.pergamon.application.responses.AdminSearchResponse;
import com.pergamon.application.responses.HealthCheckResponse;
import com.pergamon.application.users.admin_search.AdminSearchQuery;
import com.pergamon.core.interfaces.IHealthCheckService;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

	private final IHealthCheckService _service;
	
	public HealthController(IHealthCheckService service)
	{
		_service = service;
	}
	
	@GetMapping("/health-check")  
	public CompletableFuture<HealthCheckResponse> HealthCheckAsync() 
	{
		try { 
			
			return CompletableFuture.completedFuture(new HealthCheckResponse(_service.health()));
			
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
