package com.pergamon.core.interfaces;

import java.util.List;

import com.pergamon.core.entites.Transaction;

public interface ITransactionRepository {
	
	List<Transaction> GetUserTransactionsByUId(String unıqUserId);
	List<Transaction> GetAllTransactions();
}
