package com.pergamon.application.responses;

import java.util.List;

import com.pergamon.core.entites.Transaction;

public class ViewTransactionsResponse {

	public List<Transaction> transactions;
	
	public ViewTransactionsResponse( List<Transaction> transactions)
	{
		this.transactions = transactions;
	}
}
