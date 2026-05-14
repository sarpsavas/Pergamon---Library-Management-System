package com.pergamon.application.management_transactions.view_management_transactions;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.responses.ViewTransactionsResponse;
import com.pergamon.core.interfaces.IManagementTransactionRepository;

@Component
public class ViewManagementTransactionsQueryHandler {

	private IManagementTransactionRepository _maTrRepository;
	
	public ViewManagementTransactionsQueryHandler(IManagementTransactionRepository maTrRepository)
	{
		_maTrRepository = maTrRepository;
	}
	
	@QueryHandler
	public ViewTransactionsResponse handle (ViewManagementTransactionsQuery request)
	{
		return new ViewTransactionsResponse(_maTrRepository.getAllManagementTransaction());
	}
}
