package com.pergamon.api.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.organizations.delete_organization.DeleteOrganizationCommand;
import com.pergamon.application.responses.GetOrganizationsResponse;
import com.pergamon.application.users.admin_delete.DeleteAdminCommand;
import com.pergamon.application.users.admin_search.AdminSearchQuery;
import com.pergamon.application.users.teacher_register.TeacherRegisterCommand;
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
	public CompletableFuture<GetOrganizationsResponse> adminSearchAsync(@RequestBody AdminSearchQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(GetOrganizationsResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/organization-delete") 
	public CompletableFuture<String> deleteOrganizationAsync(@RequestBody DeleteOrganizationCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/add-organization") 
	public CompletableFuture<String> addOrganizationAsync(@RequestBody TeacherRegisterCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
}
