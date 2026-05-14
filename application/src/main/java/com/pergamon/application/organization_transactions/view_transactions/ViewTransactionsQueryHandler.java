package com.pergamon.application.organization_transactions.view_transactions;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.responses.ViewFeedbacksResponse;
import com.pergamon.application.responses.ViewTransactionsResponse;
import com.pergamon.core.interfaces.ITransactionRepository;

@Component
public class ViewTransactionsQueryHandler {

	private ITransactionRepository _trRepository;
	
	public ViewTransactionsQueryHandler(ITransactionRepository trRepository)
	{
		_trRepository = trRepository;
	}
	
	@QueryHandler
	public ViewTransactionsResponse handle(ViewTransactionsQuery request)
	{
		return new ViewTransactionsResponse(_trRepository.GetAllTransactions(request.organizationId()));
	}
}
