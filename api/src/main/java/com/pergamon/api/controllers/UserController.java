package com.pergamon.api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.core.responses.LogInResponse;
import com.pergmaon.application.users.log_in.LogInQuery;

import DTOs.Responses.BookResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public UserController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	//TODO: user-search
	
	//TODO: visitor-add
	
	//TODO: visitor-update
	
	//TODO: visitor-sign-up
	
//	@GetMapping("/log-in")  //visitor + admin
//	public CompletableFuture<LogInResponse> BookSearch(@RequestBody LogInQuery request) 
//	{
//		try { 
//			return _qGateway.query(
//			        new LogInQuery(request.email(), request.password(), request.organization_pergamon_id()), 
//		        ResponseTypes.instanceOf(LogInResponse.class));
//		}
//		catch (Exception e) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//		}
//	}
	
	//TODO: visitor-delete
	
	
}
