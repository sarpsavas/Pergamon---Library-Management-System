package com.pergamon.api.controllers;

import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pergamon.application.feedbacks.view_feedbacks.ViewFeedbacksQuery;
import com.pergamon.application.responses.AdminSearchResponse;
import com.pergamon.application.responses.ViewFeedbacksResponse;
import com.pergamon.application.users.admin_register.AdminRegisterCommand;
import com.pergamon.application.users.admin_search.AdminSearchQuery;

@RestController
@RequestMapping("/api/v1/transaction")
public class FeedbackController 
{
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public FeedbackController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	@PostMapping("/add-feedback") 
	public CompletableFuture<String> AddAdmin(@RequestBody AdminRegisterCommand request) 
	{
		try {
			
			return _cGateway.sendAndWait(request);
		} 
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/get-feedbacks")  
	public CompletableFuture<ViewFeedbacksResponse> AdminSearch(@RequestBody ViewFeedbacksQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(ViewFeedbacksResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
