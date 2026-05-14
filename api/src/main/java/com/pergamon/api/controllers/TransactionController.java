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

import com.pergamon.application.organization_transactions.view_transactions.ViewTransactionsQuery;
import com.pergamon.application.responses.AdminSearchResponse;
import com.pergamon.application.responses.ViewTransactionsResponse;
import com.pergamon.application.users.admin_search.AdminSearchQuery;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
	
	private final CommandGateway _cGateway;
	private final QueryGateway _qGateway;
	
	public TransactionController(CommandGateway cGateway, QueryGateway qGateway)
	{
		_cGateway = cGateway;
		_qGateway = qGateway;
	}
	
	@GetMapping("/get-transactions")  
	public CompletableFuture<ViewTransactionsResponse> viewTransactions(@RequestBody ViewTransactionsQuery request) 
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
