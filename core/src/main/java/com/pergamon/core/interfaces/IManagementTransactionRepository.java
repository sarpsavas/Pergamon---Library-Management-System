package com.pergamon.core.interfaces;

import java.util.List;
import java.util.UUID;



import com.pergamon.core.entites.Transaction;


public interface IManagementTransactionRepository {
	
	List<Transaction> getAllManagementTransaction();
	
	void add(Transaction transaction);
	
	
	void update(Transaction transaction);
	
	
	void delete(UUID id);
	
	
}
