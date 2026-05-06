package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;

import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.core.interfaces.ITransactionRepository;
import com.pergamon.infrastructure.persistence.repository.ITransactionDA;
import com.pergamon.infrastructure.persistence.repository.IVisitorDA;

public class TransactionRepositoryImpl implements ITransactionRepository,IRepository<Transaction>
{
	private final Jdbi _jdbi;
	
	public TransactionRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Transaction> GetUserTransactionsByUId(String unıqUserId)
	{
		return _jdbi.withExtension(ITransactionDA.class, da -> da.GetUserTransactionsByUId(unıqUserId));
	}
	
	public List<Transaction> GetAllTransactions()	
	{
		return _jdbi.withExtension(ITransactionDA.class, da -> da.GetAllTransactions());
				
	}
	
	public void add(Transaction transaction)
	{
		_jdbi.useExtension(ITransactionDA.class, da -> {da.add(transaction);});
	}
	
	public void update(Transaction transaction)
	{
		_jdbi.useExtension(ITransactionDA.class, da -> {da.update(transaction);});
	}
	
	public void delete(UUID transactionId)
	{
		_jdbi.useExtension(ITransactionDA.class, da -> {da.delete(transactionId);});
	}
}
