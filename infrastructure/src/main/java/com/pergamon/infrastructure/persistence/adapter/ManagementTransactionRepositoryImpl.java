package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Admin;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.interfaces.IManagementTransactionRepository;
import com.pergamon.infrastructure.persistence.repository.IManagementTransactionDA;

public class ManagementTransactionRepositoryImpl implements IManagementTransactionRepository{
	
	private final Jdbi _jdbi;
	
	public ManagementTransactionRepositoryImpl(Jdbi jdbi) 
	{
		_jdbi = jdbi;

	}
	
	public List<Transaction> getAllManagementTransaction()
	{
		return _jdbi.withExtension(IManagementTransactionDA.class, da -> da.getAllManagementTransaction());
	}
	
	public void add(@BindBean Transaction transaction)
	{
		_jdbi.useExtension(IManagementTransactionDA.class, da -> da.add(transaction));
	}
	
	public void update(@BindBean Transaction transaction)
	{
		_jdbi.useExtension(IManagementTransactionDA.class, da -> da.update(transaction));
	}
	
	public void delete(@Bind("id") UUID id)
	{
		_jdbi.useExtension(IManagementTransactionDA.class, da -> da.delete(id));
	}
}
