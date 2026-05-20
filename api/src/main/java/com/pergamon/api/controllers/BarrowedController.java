package com.pergamon.api.controllers;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.barroweds.add_barrowed.AddBarrowedCommand;
import com.pergamon.application.barroweds.take_barrowed.TakeBarrowedCommand;
import com.pergamon.application.barroweds.view_all_barroweds.ViewAllBarrowedsQuery;
import com.pergamon.application.barroweds.visitor_barroweds.VisitorBarrowedsQuery;
import com.pergamon.application.responses.AdminSearchResponse;
import com.pergamon.application.responses.BarrowedsResponse;
import com.pergamon.application.users.admin_delete.DeleteAdminCommand;
import com.pergamon.application.users.admin_register.AdminRegisterCommand;
import com.pergamon.application.users.admin_search.AdminSearchQuery;

@RestController
@RequestMapping("/api/v1/barrowed")
public class BarrowedController 
{
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public BarrowedController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	@GetMapping("/all-barroweds")  
	public CompletableFuture<BarrowedsResponse> getAllBarroweds(@RequestBody ViewAllBarrowedsQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(BarrowedsResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/visitor-barroweds")  
	public CompletableFuture<BarrowedsResponse> getVisitorBarroweds(@RequestBody VisitorBarrowedsQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(BarrowedsResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@PostMapping("/add-barrowed") 
	public CompletableFuture<String> AddAdmin(@RequestBody AddBarrowedCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
			
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("/take-barrowed") 
	public CompletableFuture<String> DeleteAdmin(@RequestBody TakeBarrowedCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
