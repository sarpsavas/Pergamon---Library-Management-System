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

import com.pergamon.application.management_transactions.view_management_transactions.ViewManagementTransactionsQuery;
import com.pergamon.application.responses.AdminSearchResponse;
import com.pergamon.application.responses.ViewTransactionsResponse;
import com.pergamon.application.users.admin_search.AdminSearchQuery;

@RestController
@RequestMapping("/api/v1/management-transaction")
public class ManagementTransactionController {

	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public ManagementTransactionController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	@GetMapping("/view-management-transactions")  
	public CompletableFuture<ViewTransactionsResponse> viewManagementTransactionsAsync(@RequestBody ViewManagementTransactionsQuery request) 
	{
		try { 
			return _qGateway.query(request, 
		        ResponseTypes.instanceOf(ViewTransactionsResponse.class));
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
