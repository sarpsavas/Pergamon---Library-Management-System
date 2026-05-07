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
	
	public List<Transaction> GetUserTransactionsByUId(String unıqUserId, String organizationPerId)
	{
		return _jdbi.withExtension(ITransactionDA.class, da -> da.GetUserTransactionsByUId(unıqUserId, organizationPerId));
	}
	
	public List<Transaction> GetAllTransactions(String organizationPerId)	
	{
		return _jdbi.withExtension(ITransactionDA.class, da -> da.GetAllTransactions(organizationPerId));
				
	}
	
	public void add(Transaction transaction)
	{
		_jdbi.useExtension(ITransactionDA.class, da -> {da.add(transaction,transaction.organizationPerId);});
	}
	
	public void update(Transaction transaction)
	{
		_jdbi.useExtension(ITransactionDA.class, da -> {da.update(transaction,transaction.organizationPerId);});
	}
	
	public void delete(UUID transactionId, String organizationPerId)
	{
		_jdbi.useExtension(ITransactionDA.class, da -> {da.delete(transactionId,organizationPerId);});
	}
}
