package com.pergamon.api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.users.admin_search.AdminSearchQuery;
import com.pergamon.core.entites.Organization;
import com.pergamon.core.responses.LogInResponse;

@RestController
@RequestMapping("/api/v1/management")
public class ManagementController {

	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public ManagementController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	@GetMapping("/get-organizations")  
	public ResponseEntity<List<Organization>> AdminSearch(@RequestBody AdminSearchQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(List<Organization>));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
